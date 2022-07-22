package com.emma.twilio.controller;

import com.emma.twilio.request.CallRequest;
import com.emma.twilio.service.CallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/caller")
@Slf4j
public class CallController {

    @Autowired
    private CallService callService;

    @PostMapping("/call")
    public void makeCall(@RequestBody CallRequest callRequest) {
        log.info("[CONTROLLER] - Generating call Request with body {}" + callRequest);
        callService.makeCall(callRequest.getFrom(), callRequest.getTo(), callRequest.getVoiceMessageURI());
    }

}
