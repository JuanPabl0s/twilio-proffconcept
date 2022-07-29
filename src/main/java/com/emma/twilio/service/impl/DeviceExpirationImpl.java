package com.emma.twilio.service.impl;

import com.emma.twilio.request.Cellphone;
import com.emma.twilio.service.redisservice.impl.RedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DeviceExpirationImpl {

    private static final Long DAY = 86400L;

    LocalDateTime localDateTime = LocalDateTime.now();

    @Autowired
    private RedisUtil redisUtil;
    private RedisTemplate<String, Cellphone> cellphoneRedisTemplate;

    private HashOperations hashOperations;

    public  DeviceExpirationImpl(RedisTemplate<String,
            Cellphone> cellphoneRedisTemplate){
        this.cellphoneRedisTemplate=cellphoneRedisTemplate;
        hashOperations=cellphoneRedisTemplate.opsForHash();
    }

    //Use this method to validate if the user can send more requests
    public Boolean deviceUUIDisExpired(String key, String uuid){

        boolean deviceIsExpired = false;

        Cellphone cellphone = (Cellphone) redisUtil.get(key);

        long expireTime = redisUtil.getExpire(key);

        log.info("[Service] Validating if time per UUID has expired {} : " + uuid  );
        if(uuid.equalsIgnoreCase(cellphone.getUuid()) && expireTime < 0){
            return false;
        }else {
            log.info("[Service] The device UUID : {}" + uuid +" has ");
            return  true;
        }
    }



    public String getOtp(String key) {
        try {
            return (String) redisUtil.get(key);
        } catch (Exception e) {
            return null;
        }
    }
    public void setDeviceWithIntents(String key) throws NoSuchFieldException, JsonProcessingException {

        Cellphone cel1 = new Cellphone();
        cel1.setKey("aaa");
        cel1.setUuid("sdfdsf");
        cel1.setExpirationTime(3L);

        //Map<String,Cellphone> mapCel

        cellphoneRedisTemplate.opsForValue().set(key, cel1,2332);

        //Object cellphone = cellphoneRedisTemplate.opsForValue().get(key);
         Cellphone cellphone = (Cellphone) cellphoneRedisTemplate.boundGeoOps(key);

        ObjectMapper mapper = new ObjectMapper();

        String s = mapper.writeValueAsString(cellphone);




        System.out.println(cellphone);

    }


}
