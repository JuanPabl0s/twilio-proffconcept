package com.emma.twilio.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class Cellphone implements Serializable {

    private String key;
    private String uuid;
    private Long expirationTime;
}
