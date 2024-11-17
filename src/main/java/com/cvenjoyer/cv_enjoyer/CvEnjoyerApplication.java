package com.cvenjoyer.cv_enjoyer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CvEnjoyerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CvEnjoyerApplication.class, args);
	}

}
