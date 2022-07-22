package com.emma.twilio.controller;

import com.emma.twilio.request.SMSRequest;
import com.emma.twilio.service.SenderSMS;
import com.emma.twilio.service.imp.TwilioSMSSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/sms")
@Slf4j
public class SMSController {

    private final SenderSMS SenderSMS;

    @Autowired
    public SMSController(SenderSMS SenderSMS) {
        this.SenderSMS = SenderSMS;
    }

    @PostMapping("/send")
    public void sendSMS(@RequestBody SMSRequest smsRequest) {
        log.info("[CONTROLLER] - Generating SMS Request with body {}" + smsRequest);
        SenderSMS.sendSMS(smsRequest);
    }
}
