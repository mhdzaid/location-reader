package com.api.locationreader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LocationReaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocationReaderApplication.class, args);
	}

}
