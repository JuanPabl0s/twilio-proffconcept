package com.emma.twilio.service;

import com.emma.twilio.request.MailRequest;

import java.io.IOException;

public interface EmailService {

     void sendEmail(MailRequest mailRequest) throws IOException;
}
