package com.emma.twilio.service.impl;

import com.emma.twilio.configuration.TwilioConfiguration;
import com.emma.twilio.request.SMSRequest;
import com.emma.twilio.service.SenderSMS;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("twilio")
@Slf4j
public class SenderSMSimpl implements SenderSMS {

    private final TwilioConfiguration twilioConfiguration;

    public SenderSMSimpl(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    @Override
    public void sendSMS(SMSRequest smsRequest) {

        if (isPhoneNumberValid(smsRequest.getPhoneNumber())) {
            PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getPhoneNumber());
            String message = smsRequest.getMessage();
            MessageCreator creator = Message.creator(to, from, message);
            creator.create();
            log.info("[SERVICE] - Generating SMS with body {}" + smsRequest);
        } else {
            log.error("[SERVICE] - Error sending sms with number {}" + smsRequest.getPhoneNumber());
            throw new IllegalArgumentException(
                    "Phone Number : " + smsRequest.getPhoneNumber() + " is not a valid number");

        }
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        //TODO: implement phone number validator
        return true;
    }
}
