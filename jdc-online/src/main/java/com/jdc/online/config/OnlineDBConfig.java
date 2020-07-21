package com.jdc.online.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.jdc.online.model.BaseRepositoryImpl;

@Configuration
@EnableJpaRepositories(
		basePackages = "com.jdc.online.model.repo", 
		repositoryBaseClass = BaseRepositoryImpl.class)
public class OnlineDBConfig {

}
