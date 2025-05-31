package com.ars.notification_system.dto;


public class EmailRequest {
    private String recipientEmail;
    private String message;
    private String subject;

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
