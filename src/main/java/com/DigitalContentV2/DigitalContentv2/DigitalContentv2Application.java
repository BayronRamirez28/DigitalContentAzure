package com.DigitalContentV2.DigitalContentv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class DigitalContentv2Application {

	@GetMapping("/message")
	public String message(){
		return "Congrats ! your application deployed successfully in Azure Platform. !";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DigitalContentv2Application.class, args);
	}

}
