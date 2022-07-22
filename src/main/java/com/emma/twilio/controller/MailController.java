package com.emma.twilio.controller;

import com.emma.twilio.request.MailRequest;
import com.emma.twilio.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/mail")
@Slf4j
public class MailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public void sendEmail(@RequestBody MailRequest mailRequest) throws IOException {
        log.info("[CONTROLLER] - Generating Mail Request with body {}" + mailRequest);
        emailService.sendEmail(mailRequest);
    }

}
