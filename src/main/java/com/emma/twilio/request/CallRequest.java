package com.emma.twilio.request;

import lombok.Data;

@Data
public class CallRequest {

    private String from;
    private String to;
    private String voiceMessageURI;
}
