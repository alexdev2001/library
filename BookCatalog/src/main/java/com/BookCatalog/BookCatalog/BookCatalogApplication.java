package com.BookCatalog.BookCatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//  register the application to the discovery server and enable RESTClient for microservice communication
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class BookCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookCatalogApplication.class, args);
	}

}
