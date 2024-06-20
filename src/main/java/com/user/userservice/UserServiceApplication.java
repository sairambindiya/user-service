package com.user.userservice;

import com.user.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
public class UserServiceApplication {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceApplication.class);
	public static void main(String[] args) {

		SpringApplication.run(UserServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner runner(UserService userService) {
		return args -> {
			userService.createMillionUsers();
			long count = userService.countUsers();
			logger.info("Total users in the database: {}", count);

		};
	}


}
