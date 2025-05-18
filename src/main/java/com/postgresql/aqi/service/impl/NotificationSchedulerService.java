package com.postgresql.aqi.service.impl;

import com.postgresql.aqi.entity.Notifications;
import com.postgresql.aqi.repository.NotificationsRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class NotificationSchedulerService {

    private final NotificationsRepository repo;
    private final JavaMailSender mailSender;

    @Value("${waqi.token}")
    private String waqiToken;

    private final RestTemplate restTemplate = new RestTemplate();

    public NotificationSchedulerService(NotificationsRepository repo, JavaMailSender mailSender) {
        this.repo = repo;
        this.mailSender = mailSender;
    }

    @Scheduled(fixedRate = 86400000) // 24 hours in milliseconds
    public void checkAqiAndSendAlerts() {
        List<Notifications> subscribers = repo.findAll();

        for (Notifications user : subscribers) {
            try {
                String city = user.getCity();
                String url = "https://api.waqi.info/feed/" + city + "/?token=" + waqiToken;
                Map<String, Object> response = restTemplate.getForObject(url, Map.class);

                if (response != null && "ok".equals(response.get("status"))) {
                    Object dataObj = response.get("data");

                    if (dataObj instanceof Map<?, ?> dataMap) {
                        Object aqiObj = dataMap.get("aqi");

                        if (aqiObj instanceof Integer aqi) {
                            if (aqi > user.getAqiThreshold()) {
                                sendEmail(user.getEmail(), city, aqi);
                            }
                        } else if (aqiObj instanceof Number number) {
                            int aqi = number.intValue();
                            if (aqi > user.getAqiThreshold()) {
                                sendEmail(user.getEmail(), city, aqi);
                            }
                        } else {
                            System.err.println("Unexpected AQI format for city: " + city);
                        }
                    } else {
                        System.err.println("Unexpected 'data' format: " + dataObj);
                    }
                } else {
                    System.err.println("API returned error for city: " + city + ", status: " + response.get("status"));
                }
            } catch (Exception e) {
                System.out.println("Error checking AQI for " + user.getEmail() + ": " + e.getMessage());
            }
        }
    }

    private void sendEmail(String to, String city, int aqi) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("purnaa_l@ymail.com"); // Replace with your verified sender
        message.setSubject("🚨 AQI Alert for " + city);

        String body = String.format(
                "Hi there, User!\n\n" +
                        "🔔 Alert! The current Air Quality Index (AQI) for %s is %d, which exceeds your alert threshold.\n\n" +
                        "Please consider the following precautions:\n" +
                        "• Limit outdoor activities 🏡\n" +
                        "• Use air purifiers indoors 🌀\n" +
                        "• Wear a mask if stepping out 😷\n\n" +
                        "Stay safe and take care of your health.\n\n" +
                        "Best regards,\n" +
                        "AirSphere Team 🌍\n\n" +
                        "This is an automated message. Please do not reply.\n\n" +
                        "You are receiving these notifications because you subscribed to them. If you wish to unsubscribe from these notifications, please visit our website or contact us.",
                city, aqi
        );


        message.setText(body);
        mailSender.send(message);
    }

}
