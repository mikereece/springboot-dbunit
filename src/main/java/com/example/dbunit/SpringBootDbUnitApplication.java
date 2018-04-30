package com.example.dbunit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class SpringBootDbUnitApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootDbUnitApplication.class, args);
	}
}
