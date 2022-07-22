package com.emma.twilio.service.imp;

import com.emma.twilio.request.SMSRequest;
import com.emma.twilio.service.SenderSMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class TwilioSMSSender {

    private final SenderSMS senderSMSimpl;

    @Autowired
    public TwilioSMSSender(@Qualifier("twilio") SenderSMS senderSMSimpl) {
        this.senderSMSimpl = senderSMSimpl;
    }

    public void sendSMS(SMSRequest smsRequest) {
        senderSMSimpl.sendSMS(smsRequest);
    }
}
