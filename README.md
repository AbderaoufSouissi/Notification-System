# Notification System

A lightweight email notification service designed for sending simple message emails. This service is built to integrate seamlessly into microservices architectures, providing reliable email delivery capabilities.

## ⚠️ Important: Gmail Setup Requirements

**Before you start**, if you're using Gmail, you **must** complete these steps:

1. **Enable 2-Factor Authentication**:
   - Go to [Google Account Security](https://myaccount.google.com/security)
   - Turn on 2-Step Verification
   - Follow the setup process

2. **Generate App Password**:
   - In Google Account → Security → 2-Step Verification
   - Scroll down to "App passwords"
   - Select "Mail" as the app
   - Copy the generated 16-character password

3. **Use App Password**: Always use the 16-character app password as your `MAIL_PASSWORD`, never your regular Gmail password

## Features

- Simple email message sending
- Microservices architecture compatible
- Easy configuration through environment variables
- Lightweight and fast deployment

## Prerequisites

- Java 8 or higher
- Maven or Gradle (depending on your build tool)
- SMTP email server access

## Dependencies

This service uses Spring Boot with the following key dependencies:
- Spring Boot Starter Mail
- Spring Boot Starter Validation (Jakarta Bean Validation)
- Spring Boot Starter Web

## Installation

### 1. Clone the Repository

```bash
git clone <repository-url>
cd notification-system
```

### 2. Configuration

Create an `application.yml` file in the `src/main/resources` directory with the following configuration:

```yaml
spring:
  application:
    name: notification-system
  profiles:
    active: dev

  mail:
    host: smtp.gmail.com
    port: 465
    username: ${MAIL_USERNAME}
    password: ${MAIL_PASSWORD}
    properties:
      mail:
        smtp:
          auth: true
          ssl:
            enable: true

server:
  port: ${APP_PORT:8080}
```

### 3. Environment Variables

Set the following environment variables:

```bash
export MAIL_USERNAME=your-email@example.com
export MAIL_PASSWORD=your-app-password
export APP_PORT=8080  # or your preferred port
```

**Note**: This configuration uses Gmail's SSL port (465) with SSL encryption. Make sure you've completed the Gmail setup requirements mentioned at the start of this document.

### 4. Build and Run

```bash
# Using Maven
mvn clean install
mvn spring-boot:run

# Using Gradle
./gradlew build
./gradlew bootRun
```

## Usage

### API Endpoints

#### Send Email Notification

```http
POST /api/notification/send
Content-Type: application/json

{
  "recipientEmail": "recipient@example.com",
  "subject": "Your Subject Here",
  "message": "Your email message content"
}
```

### Request Validation

The API includes validation for all request fields using Jakarta Bean Validation:

- `recipientEmail`: Must be a valid email format and not empty
- `subject`: Cannot be empty or blank
- `message`: Cannot be empty or blank

If validation fails, the API will return a `400 Bad Request` with details about the validation errors.

### Response Codes

- `200 OK`: Email sent successfully
- `400 Bad Request`: Validation errors or malformed request
- `500 Internal Server Error`: Email sending failed (throws `EmailNotSentException`)

### Example Request

```bash
curl -X POST http://localhost:${APP_PORT:-8080}/api/notification/send \
  -H "Content-Type: application/json" \
  -d '{
    "recipientEmail": "user@example.com",
    "subject": "Welcome!",
    "message": "Welcome to our service!"
  }'
```

## Microservices Integration

This notification service is designed to work within a microservices architecture:

### Service Discovery
- Compatible with Eureka, Consul, or other service discovery tools
- Can be registered as a discoverable service

### API Gateway Integration
- Can be routed through API gateways like Zuul or Spring Cloud Gateway
- Supports load balancing across multiple instances

### Event-Driven Architecture
- Can consume messages from message queues (RabbitMQ, Apache Kafka)
- Supports asynchronous notification processing

## Configuration Options

### Other Email Providers

#### Gmail
```yaml
spring:
  mail:
    host: smtp.gmail.com
    port: 465  # SSL port
    properties:
      mail:
        smtp:
          auth: true
          ssl:
            enable: true
```

#### Outlook/Hotmail
```yaml
spring:
  mail:
    host: smtp-mail.outlook.com
    port: 587
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
```

#### Custom SMTP Server
```yaml
spring:
  mail:
    host: your-smtp-server.com
    port: 25  # or 465 for SSL, 587 for TLS
```

## Docker Support

### Dockerfile
```dockerfile
FROM openjdk:11-jre-slim
COPY target/notification-system.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

### Docker Compose
```yaml
version: '3.8'
services:
  notification-service:
    build: .
    ports:
      - "8080:8080"
    environment:
      - MAIL_USERNAME=${MAIL_USERNAME}
      - MAIL_PASSWORD=${MAIL_PASSWORD}
      - APP_PORT=${APP_PORT:-8080}
```

## Health Checks

The service includes health check endpoints:

```http
GET /actuator/health
```

## Logging

Logs are configured to show email sending status and any errors. Check application logs for troubleshooting.

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Create a Pull Request

## Security Considerations

- Never commit credentials to version control
- Use environment variables or secure secret management
- Enable SMTP authentication
- Consider using OAuth2 for Gmail integration
- Implement rate limiting for production use

## Troubleshooting

### Common Issues

1. **Authentication Failed**: 
   - Verify you've enabled 2-factor authentication on Gmail
   - Ensure you're using the 16-character app password, not your regular Gmail password
   - Double-check MAIL_USERNAME and MAIL_PASSWORD environment variables

2. **Connection Timeout**: Check SMTP host and port configuration

3. **Gmail Specific Issues**: 
   - Ensure 2-factor authentication is enabled
   - Generate a new app password if the current one isn't working
   - Make sure you're using the correct Gmail account

### Debug Mode

Enable debug logging by adding to your configuration:

```yaml
logging:
  level:
    org.springframework.mail: DEBUG
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Support

For support and questions, please open an issue in the repository or contact the development team.