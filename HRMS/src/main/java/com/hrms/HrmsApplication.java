package com.hrms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HrmsApplication {

	public static void main(String[] args) {

		SpringApplication.run(HrmsApplication.class, args);
		System.out.println("Hello world");
	}

}
