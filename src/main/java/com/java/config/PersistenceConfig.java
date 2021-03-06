package com.java.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories
@EnableJpaAuditing
public class PersistenceConfig {
	
//	@Bean
//    AuditorAware<String> auditorProvider() {
//        return new AuditorAwareImpl();   -> AuditorAwareImpl.Class package config
//    }

}
