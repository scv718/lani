package com.lani;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

import com.lani.serviceimpl.RedisService;
import com.lani.serviceimpl.TestServiceimpl;

public class Test {



	public static void main(String[] args) {


		TestServiceimpl testService = new TestServiceimpl();
		testService.test();

	}




}
