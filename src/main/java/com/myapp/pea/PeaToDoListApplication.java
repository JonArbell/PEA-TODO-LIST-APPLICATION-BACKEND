package com.myapp.pea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;

@SpringBootApplication
public class PeaToDoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeaToDoListApplication.class, args);

		final var base = 3;
		int[] x = new int[base];
		var s = new Scanner(System.in);

		for(var i = 0; i < base; i++){
			var num = s.nextInt();
			System.out.println("Input "+ (i+1) + " : "+num);
			x[i] = num;
		}

		var temp = x[0];
		for(var i = 1; i < base; i++){

			if(temp < x[i]){
				temp = x[i];
			}

		}

		System.out.println("Largest num is : "+temp);
	}
/*
*
* Group 		: 	com.myapp
* Artifact 		: 	PeaTodoListApplication
* Name			: 	PeaTodoListApplication
* Description	:	My First Web Application
* JDK version	:	21.0.1
* Package name  :	com.myapp.pea
*
*/
}
