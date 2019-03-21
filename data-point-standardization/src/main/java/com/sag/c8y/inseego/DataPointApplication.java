package com.sag.c8y.inseego;

import org.springframework.boot.SpringApplication;

import com.cumulocity.microservice.autoconfigure.MicroserviceApplication;
import com.cumulocity.microservice.context.annotation.EnableContextSupport;

@MicroserviceApplication
@EnableContextSupport
public class DataPointApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DataPointApplication.class, args);
	}
}
