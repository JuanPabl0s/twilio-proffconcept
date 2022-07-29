package com.emma.twilio.service.impl;

import java.net.URI;
import java.net.URISyntaxException;

import com.emma.twilio.configuration.TwilioConfiguration;
import com.emma.twilio.service.CallService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CallServiceImpl implements CallService {

    @Autowired
    private TwilioConfiguration twilioConfiguration;

    @Override
    public void makeCall(String from, String to, String voiceMessageURI) {

        try {
            Twilio.init(twilioConfiguration.getAccountSid(), twilioConfiguration.getAuthToken());
            Call call = Call.creator(
                            new PhoneNumber(to),
                            new PhoneNumber(from),
                            new URI(voiceMessageURI))
                    .create();
            log.info("[SERVICE] - Generating call with SID : {}" + call.getSid());

        } catch (URISyntaxException e) {
            log.error("[SERVICE] - Error generating call");
            e.getMessage();
        }

    }
}
