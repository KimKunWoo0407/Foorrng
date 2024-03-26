package com.d205.foorrng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FoorrngApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoorrngApplication.class, args);
	}
}
