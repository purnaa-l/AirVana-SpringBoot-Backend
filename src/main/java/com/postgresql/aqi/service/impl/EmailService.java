package com.postgresql.aqi.service.impl;

import jakarta.mail.MessagingException; // Correct import for MessagingException
import jakarta.mail.internet.MimeMessage;  // Correct import for MimeMessage
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendPersonalizedEmail(String to, String name, String query) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("purnaa_l@ymail.com");  // Your email address
        helper.setTo(to);  // The recipient's email
        helper.setSubject("Thank you for your query, " + name + "!");

        // Text content with simple formatting like bullet points, newlines
        String content = String.format(
                "Dear %s,\n\n" +
                        "Thank you for your query: \"%s\".\n\n" +
                        "Our team will review it and get back to you shortly.\n\n" +
                        "Rest assured that your query will be resolved soon.\n\n" +
                        "Thank you for using AirSphere!\n\n" +
                        "Best regards,\n" +
                        "AirSphere Support Team\n\n" +
                        "If you have any further questions, feel free to reply to this email."
                , name, query);

        helper.setText(content);  // Set the plain text message content

        // Send the email
        emailSender.send(message);
    }
}
