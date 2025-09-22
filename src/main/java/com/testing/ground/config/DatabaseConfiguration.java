package com.testing.ground.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


//@Configuration
//@EnableJpaRepositories(basePackages = "com.testing.ground.repository")
//@EntityScan(basePackages = "com.testing.ground.entity")
//@EnableTransactionManagement
//@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
public class DatabaseConfiguration {

}
