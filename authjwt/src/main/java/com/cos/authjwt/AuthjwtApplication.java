package com.cos.authjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.cos.authjwt.config.AppProperties;
import com.cos.authjwt.config.ClientProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {AppProperties.class, ClientProperties.class})
public class AuthjwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthjwtApplication.class, args);
	}

}
