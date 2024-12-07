package com.erick.aftership;

import com.erick.aftership.view.TrackingUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AftershipApplication {

	public static void main(String[] args) {
		// Inicia Spring Boot
		ApplicationContext context = SpringApplication.run(AftershipApplication.class, args);
	}
}
