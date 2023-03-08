package com.yvan.beekeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.yvan.beekeeper")
@EnableScheduling
public class BeekeeperApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeekeeperApplication.class, args);
	}

}
