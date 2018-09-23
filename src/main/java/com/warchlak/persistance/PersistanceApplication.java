package com.warchlak.persistance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.warchlak.persistance.repository")
public class PersistanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersistanceApplication.class, args);
	}
}
