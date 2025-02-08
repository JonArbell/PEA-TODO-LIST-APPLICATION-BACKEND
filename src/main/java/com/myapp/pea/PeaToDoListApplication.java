package com.myapp.pea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class PeaToDoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeaToDoListApplication.class, args);

	}
/*
*
* Group 		: 	com.myapp
* Artifact 		: 	PeaTodoListApplication
* Name			: 	PeaTodoListApplication
* Description	:	My First Web Application
* JDK version	:	23
* Package name  :	com.myapp.pea
*
*/
}
