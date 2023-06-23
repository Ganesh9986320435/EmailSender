package com.example.emailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class Controller {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private EmailService emailService;

    @PostMapping("/email")
    public String set(@RequestBody User user)
    {
        mongoTemplate.save(user);
        return "ok";
    }

    @PostMapping("/emailSender")
    public String send(@RequestBody User user) throws MessagingException {
        emailService.sendEmail(user);
        return "sent";
    }
}
