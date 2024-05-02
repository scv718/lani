package com.lani.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import com.lani.event.RedisEventListener;


@Configuration
public class RedisSubscriptionConfig {

    @Bean
    RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory, RedisSubscriber redisSubscriber, RedisEventListener redisEventListener) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        // RedisSubscriber와 RedisEventListener를 모두 등록합니다.
        container.addMessageListener(redisSubscriber, new ChannelTopic("insert"));
        container.addMessageListener(redisSubscriber, new ChannelTopic("select"));
        container.addMessageListener(redisEventListener, new ChannelTopic("insert"));
        container.addMessageListener(redisEventListener, new ChannelTopic("select"));

        return container;
    }
}
