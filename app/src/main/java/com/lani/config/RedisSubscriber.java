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

    public void subscribe(String channel) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisTemplate.getConnectionFactory());
        container.addMessageListener(this, new ChannelTopic(channel));
        container.afterPropertiesSet();
        container.start();
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String channel = new String(message.getChannel());
        String msg = new String(message.getBody());
        System.out.println("Received message from channel " + channel + ": " + msg);
    }
}