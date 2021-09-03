package com.example.AmazonS3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@SpringBootApplication
public class AmazonS3Application {

	public static void main(String[] args) {
		SpringApplication.run(AmazonS3Application.class, args);
	}

}
