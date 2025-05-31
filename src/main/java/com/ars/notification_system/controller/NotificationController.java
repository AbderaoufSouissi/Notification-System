package com.ars.notification_system.controller;

import com.ars.notification_system.dto.EmailRequest;
import com.ars.notification_system.exception.EmailNotSentException;
import com.ars.notification_system.service.EmailSenderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    private final EmailSenderService emailSenderService;

    public NotificationController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }
    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody @Valid EmailRequest request){
        try{
            emailSenderService.sendEmail(request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new EmailNotSentException("Email not sent",e);
        }

    }
}
