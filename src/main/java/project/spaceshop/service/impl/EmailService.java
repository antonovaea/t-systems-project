package project.spaceshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import project.spaceshop.entity.User;
import project.spaceshop.service.api.UserService;

@Service
public class EmailService {

    private JavaMailSender emailSender;
    private final UserService userService;

    @Autowired
    public EmailService(JavaMailSender emailSender, UserService userService) {
        this.emailSender = emailSender;
        this.userService = userService;
    }

    public void sendSimpleMessage(String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(userService.findUserFromSecurityContextHolder().getEmail());
        message.setSubject("Planet Shop");
        message.setText(text);
        emailSender.send(message);
    }
}
