package com.chat.controller.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/email")
public class EmailRestController {

    @Value("${spring.email.username}")
    private String username;

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/send")
    public void sendEmail() {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(username);
        message.setTo("odinmark91@gmail.com");
        message.setSubject("Test Simple Email");
        message.setText("Hello, Im testing Simple Email");

        mailSender.send(message);
    }


}
