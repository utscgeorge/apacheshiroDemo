package com.example.apacheshiroDemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.apacheshiroDemo.mapper"})
public class ApacheshiroDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApacheshiroDemoApplication.class, args);
	}

}
