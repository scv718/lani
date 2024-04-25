package com.lani.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lani.response.RestResponse;
import com.lani.serviceimpl.RedisService;

@RestController
public class RedisController {

	final static String KEY = "key";
	final static String VALUE = "value";
	final static Duration DURATION = Duration.ofMillis(5000);

	@Autowired
	RedisService redisService;

	@PostMapping("/redistest")
	public RestResponse addMaptest() {


        redisService.setValues("key", "value");
        String findValue = redisService.getValues(KEY);
		return null;

	}
}
