package com.emma.twilio.request;

import lombok.Data;

@Data
public class MailRequest {

    private String from;
    private String subject;
    private String to;
}
