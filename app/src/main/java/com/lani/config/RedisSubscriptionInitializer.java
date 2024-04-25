package com.lani.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class RedisSubscriptionInitializer {

    private final RedisSubscriber redisSubscriber;

    @Autowired
    public RedisSubscriptionInitializer(RedisSubscriber redisSubscriber) {
        this.redisSubscriber = redisSubscriber;
    }

    @PostConstruct
    public void initializeRedisSubscription() {
        // 레디스 채널을 구독합니다.
        redisSubscriber.subscribe("insert");
        redisSubscriber.subscribe("select");
    }
}