package com.dozac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaFileTransferApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(KafkaFileTransferApplication.class, args);
	}

}
