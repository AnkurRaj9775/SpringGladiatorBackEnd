package com.lti;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcoBusApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcoBusApplication.class, args);
		
//		LocalDate date = LocalDate.now();
//		
//		LocalDate returnvalue = date.minusMonths(1); 
//		System.out.println(returnvalue);
		
		//System.out.println(date.getMonth());
		
		LocalDate monday =LocalDate.now();
	    while (monday.getDayOfWeek() != DayOfWeek.MONDAY) {
	      monday = monday.minusDays(1);
	    }
	    System.out.println(monday);
	}
	
}
