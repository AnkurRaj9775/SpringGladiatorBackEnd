package com.lti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.lti.controller.EcoController;
import com.lti.model.Bus;
import com.lti.model.Customer;
import com.lti.model.Driver;
import com.lti.model.OperationalDays;
import com.lti.model.Routes;
@SpringBootTest
//@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
class TestNew {
	@Autowired
	EcoController controller;

	Customer customer=new Customer();
	Bus bus =new Bus();
	Driver driver=new Driver();
	OperationalDays op=new OperationalDays();
	Routes ro=new Routes();
	

	@Test
	void regiterTest() {
	
		customer.setAge(23);
		customer.setContact("6384615645");
		customer.setEmail("munish.bhanawat@gmail.com");
		customer.setGender("M");
		customer.setName("Munish Bhanawa");
		customer.setPassword("Munish@123");
		customer.setWalletBalance(0);
		
		System.out.println(controller.registerUser(customer));
	}

}
