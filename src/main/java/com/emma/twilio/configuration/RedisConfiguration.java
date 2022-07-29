package com.emma.twilio.configuration;

import java.time.Duration;

import com.emma.twilio.request.Cellphone;
import com.emma.twilio.service.redisservice.impl.RedisUtil;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


@Configuration
public class RedisConfiguration {
	
	@Value("${redis.host}")
	private String host;
	
	@Value("${redis.password}")
	private String password;
	
	@Value("${redis.port}")
    private int port;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
    	 RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
         redisStandaloneConfiguration.setHostName(host);
         redisStandaloneConfiguration.setPort(port);
         //redisStandaloneConfiguration.setPassword(RedisPassword.of(password));


         JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
         jedisClientConfiguration.connectTimeout(Duration.ofSeconds(60));// 60s connection timeout
         return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration.build());
    }
    
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setEnableTransactionSupport(true);
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

    @Bean
    public RedisTemplate<String, Cellphone> cellPhoneTemplate() {
        RedisTemplate<String, Cellphone> cellphoneRedisTemplate = new RedisTemplate<String, Cellphone>();
        //cellphoneRedisTemplate.setEnableTransactionSupport(true);
        //jedisConnectionFactory().getPoolConfig().setMaxIdle(30);
        //jedisConnectionFactory().getPoolConfig().setMinIdle(10);
        cellphoneRedisTemplate.setConnectionFactory(jedisConnectionFactory());

        return cellphoneRedisTemplate;
    }


    @Bean(name = "redisUtil")
    public RedisUtil redisUtil(RedisTemplate<String, Object> redisTemplate) {
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.setRedisTemplate(redisTemplate);

        return redisUtil;
    }
}
