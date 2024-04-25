package com.lani;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

import com.lani.serviceimpl.RedisService;

public class Test {

	final static String KEY = "key";
	final static String VALUE = "value";
	final static Duration DURATION = Duration.ofMillis(5000);

	@Autowired
	private static RedisService redisService;

	public static void main(String[] args) {

		SpringApplication.run(Test.class, args);
	}

	@Bean
	   CommandLineRunner run() {
        return args -> {

        };
    }


}
