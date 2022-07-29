package com.emma.twilio.controller;

import com.emma.twilio.request.DeviceRequest;
import com.emma.twilio.service.impl.DeviceExpirationImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1")
public class TwillioSMSController {


    @Autowired
    private DeviceExpirationImpl deviceExpiration;

    public TwillioSMSController(DeviceExpirationImpl deviceExpiration){
        this.deviceExpiration=deviceExpiration;
    }

    @PostMapping("/device")
    public Boolean setTime(@RequestBody DeviceRequest deviceRequest) throws NoSuchFieldException {

        log.info("[Controller] Intent to set expiration to device : {}" + deviceRequest.getUuid());
        //Boolean wasInserted = timesIntentService.setTimeToDevice(deviceRequest.getKey(), deviceRequest.getUuid());

        return null;

    }

    @GetMapping("/device")
    public String getDevice(@RequestBody DeviceRequest deviceRequest){

        //String timeDevice = timesIntentService.getTimeDevice(deviceRequest.getKey());

        return null;
    }

    @GetMapping("/devicetime")
    public Object getExpirationTime(@RequestBody DeviceRequest deviceRequest){

        //Object timeDevice = timesIntentService.getExpirationTime(deviceRequest.getKey());

        return null;
    }

    @GetMapping("/devicetimeWithArgs")
    public void getExpirationTimeWithVaragr(@RequestBody DeviceRequest deviceRequest) throws NoSuchFieldException, JsonProcessingException {

        deviceExpiration.setDeviceWithIntents(deviceRequest.getKey());


    }





}
