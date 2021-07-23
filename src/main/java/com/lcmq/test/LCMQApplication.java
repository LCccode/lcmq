package com.lcmq.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class LCMQApplication {
	public static void main(String[] args) {
		SpringApplication.run(LCMQApplication.class, args);
	}
}
