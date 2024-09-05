package com.BorrowRecord.BorrowRecord;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// register the microservice to the discovery client
@SpringBootApplication
@EnableDiscoveryClient
public class BorrowRecordApplication {

	public static void main(String[] args) {
		SpringApplication.run(BorrowRecordApplication.class, args);
	}

}
