package com.example.emailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private Environment env;

    public void sendEmail(User user) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(user.getEmail());
        helper.setSubject("gani");

        String replacedTemplate = user.emailTemplate.replace("<span th:text=\"${app.name}\" />", env.getProperty("app.name"))
                .replace("<span th:text=\"${name}\" />", env.getProperty("name"))
                .replace("<a th:href=\"${url}\">\"Sign in Link\"</a>", "<a href="+env.getProperty("url")+">\"Sign in Link\"</a>")
                .replace("<a th:href=\"${contactUs}\">Contact Us </a>", "<a href=" + env.getProperty("contactUs")+">Contact Us </a>");

        helper.setText(replacedTemplate, true);

        mailSender.send(message);
    }

}
