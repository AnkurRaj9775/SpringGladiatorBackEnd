package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lti.bridge.CustomerDetails;
import com.lti.bridge.PassengerDetails;
import com.lti.bridge.SeatDetails;
import com.lti.bridge.Status;
import com.lti.bridge.TicketDetails;
import com.lti.model.Bus;
import com.lti.model.Customer;
import com.lti.model.Driver;
import com.lti.model.OperationalDays;
import com.lti.model.Routes;
import com.lti.service.EcoService;
import com.lti.service.EcoServiceImpl;

@RestController
@CrossOrigin
public class EcoController {

	@Autowired
	private EcoService ecoServ;

	@PostMapping("/register")
	public Status registerUser(Customer customer) {

		return ecoServ.registerUser(customer);
	}

	public boolean loginUser(String email, String password) {

		return ecoServ.loginUser(email, password);
	}

	public boolean addAbus(Bus bus) {

		return ecoServ.addABus(bus);

	}

	public boolean addBuswithDriver(Bus bus, Driver driver) {
		return ecoServ.addBuswithDriver(bus, driver);
	}

	public boolean addRoutewithBus(List<Routes> routes, int busId) {
		
		return ecoServ.addRoutewithBus(routes, busId);
	}
	
	
	public boolean addOperationalDaysWithBus(List<OperationalDays> operationalDays,int busId)
	{
		return ecoServ.addOperationalDaysWithBus(operationalDays, busId);
	}
	
	
	public List<Bus> searchABus(String fromCity, String toCity, String day) {
		return ecoServ.searchABus(fromCity, toCity, day);
	}
	
	public Bus findBus(int busid){
		return ecoServ.findBus(busid);
	}
	
	public Status addTicketDetails(CustomerDetails customerDetails,TicketDetails ticketDetails,List<PassengerDetails> passengerDetails,List<SeatDetails> seatDetails){
		return ecoServ.addTicketDetails(customerDetails, ticketDetails, passengerDetails, seatDetails);
	}
	
}

