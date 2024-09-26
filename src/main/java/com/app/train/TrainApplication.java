package com.app.train;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;


@SpringBootApplication
@EnableJdbcRepositories(basePackages = "com.app.train.dao.interfaces")
public class TrainApplication {

	public static void main(String[] args) {

		SpringApplication.run(TrainApplication.class, args);
	}

}
