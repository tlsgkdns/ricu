package com.shin.ricu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RicuApplication {

	public static void main(String[] args) {
		SpringApplication.run(RicuApplication.class, args);
	}

}
