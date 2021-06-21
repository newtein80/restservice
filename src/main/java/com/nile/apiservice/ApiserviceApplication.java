package com.nile.apiservice;

import com.nile.apiservice.common.repository.BaseRepositoryImpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
// @EnableJpaRepositories(repositoryImplementationPostfix = "")
public class ApiserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiserviceApplication.class, args);
	}

}
