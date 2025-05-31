package com.ars.notification_system.service;

import com.ars.notification_system.dto.EmailRequest;
import com.ars.notification_system.exception.EmailNotSentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService{

    private static final Logger log = LoggerFactory.getLogger(EmailSenderService.class);

    @Value("${spring.mail.username}")
    private String senderEmail;

    private final JavaMailSender javaMailSender;

    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public void sendEmail(EmailRequest request) {
        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(senderEmail);
            mailMessage.setSubject(request.getSubject());
            mailMessage.setTo(request.getRecipientEmail());
            mailMessage.setText(request.getMessage());
            javaMailSender.send(mailMessage);
            log.info("Email sent to {}", request.getRecipientEmail());

        } catch (MailException e) {
            throw new EmailNotSentException("Failed to send email", e);
        }

    }
}
