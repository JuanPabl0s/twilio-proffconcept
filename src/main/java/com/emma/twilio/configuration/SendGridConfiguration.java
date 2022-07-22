package com.emma.twilio.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("sendgrid")
@Data
public class SendGridConfiguration {
    private String apiKey;
}
