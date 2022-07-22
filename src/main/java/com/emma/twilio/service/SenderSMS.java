package com.emma.twilio.service;

import com.emma.twilio.request.SMSRequest;

public interface SenderSMS {
    void sendSMS(SMSRequest smsRequest);
}
