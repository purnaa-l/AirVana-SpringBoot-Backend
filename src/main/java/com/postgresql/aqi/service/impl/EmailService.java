package com.postgresql.aqi.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

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

        // Construct HTML content with enhancements
        String content = String.format(
                "<html>" +
                        "<head>" +
                        "<style>" +
                        "/* General styling */" +
                        "body {font-family: 'Georgia', 'Times New Roman', serif; background-color: #f4f7f6; margin: 0; padding: 0; color: #333;}" +
                        "/* Background image with transparent overlay */" +
                        "body {" +
                        "   background: url('cid:background-image') no-repeat center center fixed;" +
                        "   background-size: cover;" +
                        "   background-color: rgba(0, 0, 0, 0.1); /* Transparent overlay */" +
                        "}" +
                        ".container {width: 100%%; max-width: 600px; margin: 0 auto; background-color: rgba(255, 255, 255, 0.9); padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);}" +
                        ".header {text-align: center; margin-bottom: 20px;}" +
                        ".content {font-size: 16px; line-height: 1.6; padding: 15px; text-align: center;}" +
                        ".air-quality {color: #28a745; font-weight: bold; font-size: 18px;}" +
                        ".cta-button {background-color: #0073e6; color: #fff; padding: 12px 20px; text-align: center; display: inline-block; text-decoration: none; border-radius: 5px; font-weight: bold; margin-top: 20px;}" +
                        ".cta-button:hover {background-color: #005bb5;}" +
                        ".footer {font-size: 14px; text-align: center; color: #777; margin-top: 30px; border-top: 1px solid #ccc; padding-top: 15px;}" +
                        ".footer p {margin: 5px 0;}" +
                        ".footer a {color: #28a745; text-decoration: none;}" +
                        ".social-icons {text-align: center; margin-top: 15px;}" +
                        ".social-icons img {width: 24px; height: 24px; margin: 0 10px;}" +
                        "@media (max-width: 600px) {" +
                        "  .container {width: 100%%; padding: 15px;}" +
                        "  .content {font-size: 14px;}" +
                        "  .cta-button {width: 100%%; padding: 14px 0;}" +
                        "}" +
                        "</style>" +
                        "</head>" +
                        "<body>" +
                        "<div class='container'>" +
                        "<div class='header'>" +
                        "</div>" +
                        "<div class='content'>" +
                        "<p>Dear %s,</p>" +
                        "<p>Thank you for reaching out to us! We have received your query: <strong>%s</strong>.</p>" +
                        "<p>Our team will review it and get back to you as soon as possible.</p>" +
                        "<p><strong>Have a great day!</strong></p>" +
                        "<div class='cta-button'>Contact Support</div>" +
                        "</div>" +
                        "<div class='footer'>" +
                        "<p>Best regards,<br/>" +
                        "AirSphere Support Team</p>" +
                        "<p>If you have any further questions, feel free to reply to this email.</p>" +
                        "<p><em>Remember, clean air is vital for health. Breathe easy!</em></p>" +
                        "<p>Follow us:</p>" +
                        "<div class='social-icons'>" +
                        "<a href='https://facebook.com/airsphere'><img src='cid:facebook-icon' alt='Facebook'></a>" +
                        "<a href='https://twitter.com/airsphere'><img src='cid:twitter-icon' alt='Twitter'></a>" +
                        "<a href='https://instagram.com/airsphere'><img src='cid:instagram-icon' alt='Instagram'></a>" +
                        "</div>" +
                        "</div>" +
                        "</div>" +
                        "</body>" +
                        "</html>", name, query);

        helper.setText(content, true);  // Set the content as HTML

        // Add background image as an inline resource

        // Add social media icons as inline images
        helper.addInline("facebook-icon", new ClassPathResource("static/images/facebook-icon.png"));
        helper.addInline("twitter-icon", new ClassPathResource("static/images/twitter-icon.png"));
        helper.addInline("instagram-icon", new ClassPathResource("static/images/instagram-icon.png"));

        // Send the email
        emailSender.send(message);
    }
}
