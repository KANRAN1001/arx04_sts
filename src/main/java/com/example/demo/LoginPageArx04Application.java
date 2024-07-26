package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demo.dao") 
public class LoginPageArx04Application {

	public static void main(String[] args) {
		SpringApplication.run(LoginPageArx04Application.class, args);
	}

}
