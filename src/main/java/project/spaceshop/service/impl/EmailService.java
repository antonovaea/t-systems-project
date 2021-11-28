package project.spaceshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import project.spaceshop.service.api.UserService;

@Service
public class EmailService {

    /**
     * Injected by spring JavaMailSender bean
     */
    private JavaMailSender emailSender;
    /**
     * Injected by spring UserService bean
     */
    private final UserService userService;

    /**
     * Injected constructor.
     *
     * @param emailSender that must be injected.
     * @param userService that must be injected.
     */
    @Autowired
    public EmailService(JavaMailSender emailSender, UserService userService) {
        this.emailSender = emailSender;
        this.userService = userService;
    }

    /**
     * Method creates params for sending email messages.
     *
     * @param text text message that must be sent.
     */
    public void sendSimpleMessage(String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(userService.findUserFromSecurityContextHolder().getEmail());
        message.setSubject("Planet Shop");
        message.setText(text);
        emailSender.send(message);
    }
}
