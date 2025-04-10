package com.chronodebileplus;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class ChronoDebilePlusApplication {
	@Value("${central.endpoint}")
	private String centralEndpoint;

	public static void main(String[] args) {
		SpringApplication.run(ChronoDebilePlusApplication.class, args);
	}

	@Bean
	public RestClient restClient() {
		return RestClient.create(this.centralEndpoint);
	}
}
