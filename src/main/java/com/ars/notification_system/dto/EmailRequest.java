package com.ars.notification_system.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmailRequest {
    @NotBlank
    @Email
    private String recipientEmail;

    @NotBlank
    private String subject;

    @NotBlank
    private String message;

    protected EmailRequest() {}

    public EmailRequest(String recipientEmail, String message, String subject) {
        this.recipientEmail = recipientEmail;
        this.message = message;
        this.subject = subject;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
