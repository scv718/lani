package com.lani;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"com.lani.dao"})
public class AppApplication {



	public static void main(String[] args) {

		SpringApplication.run(AppApplication.class, args);


	}

}
