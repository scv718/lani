package com.lani;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan(value = {"com.lani.dao"})
public class AppApplication {


	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}
