package com.java;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

//@Configuration
//@EnableAutoConfiguration
//@ComponentScan({"com.java.springbootprofiles"})

@SpringBootApplication
public class OjtSpringBootApplication {

	public static void main(String[] args) {

		SpringApplication.run(OjtSpringBootApplication.class, args);

	}

//	@Bean
//	@Profile("dev")
//	public String dev() {
//		System.out.println("++++++++++++++ dev environment");
//		return "dev";
//	}
//
//	@Bean
//	@Profile("prod")
//	public String prod() {
//		System.out.println("++++++++++++++ prod environment");
//		return "prod";
//	}

	// ------------------------------

	@Bean
	public LocaleResolver localeResolver() {
		// SessionLocaleResolver localeResolver = new SessionLocaleResolver();

		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	// configuring ResourceBundle
//	@Bean
//	public ResourceBundleMessageSource messageSource() {
//		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//		messageSource.setBasename("messages");
//		return messageSource;
//	}

}
