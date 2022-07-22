package com.emma.twilio.service;

public interface CallService {

    void makeCall(String from, String to, String voiceMessageURI);
}
