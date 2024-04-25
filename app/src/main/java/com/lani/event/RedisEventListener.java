package com.lani.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.Data;


@Component
public class RedisEventListener {


    @EventListener
    public void handleMessage(String msg) {

    	if(msg.equals("insert") || msg.equals("update")) {

    	}
    }
}