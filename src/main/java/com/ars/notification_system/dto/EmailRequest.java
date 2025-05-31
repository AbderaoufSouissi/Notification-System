package com.ars.notification_system.dto;


public class EmailRequest {
    private String senderEmail;
    private String recipientEmail;
    private String message;
    private String subject;

    protected EmailRequest() {}

    public EmailRequest(String senderEmail, String recipientEmail, String message, String subject) {
        this.senderEmail = senderEmail;
        this.recipientEmail = recipientEmail;
        this.message = message;
        this.subject = subject;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
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
