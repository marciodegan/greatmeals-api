package com.greatmeals.greatmealsapi;

import com.greatmeals.greatmealsapi.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class GreatmealsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreatmealsApiApplication.class, args);
	}

}
