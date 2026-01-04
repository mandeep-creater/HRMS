package com.hrms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.hrms.Repo")
public class HrmsApplication {

	public static void main(String[] args) {

		SpringApplication.run(HrmsApplication.class, args);
		System.out.println("Hello world");
	}

}
