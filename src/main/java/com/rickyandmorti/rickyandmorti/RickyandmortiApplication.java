package com.rickyandmorti.rickyandmorti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


//la anotacion EnableTransactionManagement es para habitar transactional
@SpringBootApplication
@EnableTransactionManagement
public class RickyandmortiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RickyandmortiApplication.class, args);
		System.out.println("running");
	}

}
