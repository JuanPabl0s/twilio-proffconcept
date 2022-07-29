package com.emma.twilio.service.impl;

import com.emma.twilio.configuration.SendGridConfiguration;
import com.emma.twilio.request.MailRequest;
import com.emma.twilio.service.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.twilio.exception.TwilioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private SendGridConfiguration sendGridConfiguration;

    @Override
    public void sendEmail(MailRequest mailRequest) throws IOException {

        Email from = new Email(mailRequest.getFrom());
        Email to = new Email(mailRequest.getTo());
        Content content = new Content("text/plain", "Integration with java unicomer");
        Mail mail = new Mail(from, mailRequest.getSubject(), to, content);
        SendGrid sendGrid = new SendGrid(sendGridConfiguration.getApiKey());
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            log.info("[SERVICE] - Email successfully sent to {}" + from);
        } catch (TwilioException e) {

            log.error("[SERVICE] - Error sending email");
             e.getStackTrace();
        }
    }

}
