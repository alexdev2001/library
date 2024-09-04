package com.BorrowRecord.BorrowRecord;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BorrowRecordApplication {

	public static void main(String[] args) {
		SpringApplication.run(BorrowRecordApplication.class, args);
	}

}
