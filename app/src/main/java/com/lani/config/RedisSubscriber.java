package com.lani.config;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;


@Component
public class RedisSubscriber implements MessageListener {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisSubscriber(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {

    	String cha = new String(message.getChannel());

        if(cha.equals("movie")) {
        	String msg = new String(message.getBody());

        	if (msg.equals("insert")) {

        	}

        	if (msg.equals("update")) {

        	}

        }
    }
}