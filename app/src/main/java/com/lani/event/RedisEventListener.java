package com.lani.event;

import org.springframework.stereotype.Component;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

@Component
public class RedisEventListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
    	System.out.println("메세지들어옴");
        String msg = new String(message.getBody());
        System.out.println("Received Redis message: " + msg);

        if ("insert".equals(msg) || "update".equals(msg)) {
            System.out.println("An insert or update operation was performed.");
        }

        if ("select".equals(msg)) {
            System.out.println("A select operation was performed.");
        }
    }
}
