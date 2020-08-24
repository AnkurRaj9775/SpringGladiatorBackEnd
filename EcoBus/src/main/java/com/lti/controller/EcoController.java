package com.lti.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.bridge.BusDetails;
import com.lti.bridge.StatusString;
import com.lti.bridge.WalletDetails;
import com.lti.bridge.LoginStatus;
import com.lti.bridge.PassengerDetails;
import com.lti.bridge.SeatDetails;
import com.lti.bridge.Status;
import com.lti.dto.CancelTicket;
import com.lti.dto.CustomerDetails;
import com.lti.dto.LoginDetails;
import com.lti.dto.SearchBus;
import com.lti.dto.TicketDetails;
import com.lti.dto.WalletAmount;
import com.lti.dto.UpdateWallet;
import com.lti.exception.EcoServiceException;
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
	public Status registerUser(@RequestBody Customer customer) {

		return ecoServ.registerUser(customer);
	}

	@PostMapping("/login")
	public LoginStatus loginUser(@RequestBody LoginDetails loginDetails) {
		try {
		Customer customer=ecoServ.loginUser(loginDetails.getEmail(), loginDetails.getPassword());
		LoginStatus loginStatus=new LoginStatus();
		loginStatus.setResultStatus(true);
		loginStatus.setCustomerId(customer.getCustomerId());
		loginStatus.setName(customer.getName());
		return loginStatus;
		//return ecoServ.loginUser(loginDetails.getEmail(), loginDetails.getPassword());
		}catch(EcoServiceException e)
		{
			LoginStatus loginStatus =new LoginStatus();
			loginStatus.setResultStatus(false);
			return loginStatus;
		}
		
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
	
	@PostMapping("/searchBus")
	public List<BusDetails> searchABus(@RequestBody SearchBus searchBus) {
		return ecoServ.searchABus(searchBus.getFromCity(),searchBus.getToCity(),searchBus.getDay(),searchBus.getDateOfJourney());
	}
	
	public Bus findBus(int busid){
		return ecoServ.findBus(busid);
	}
	
	@PostMapping("/bookTicket")
	public Status addTicketDetails(@RequestParam CustomerDetails customerDetails,@RequestParam TicketDetails ticketDetails,@RequestParam List<PassengerDetails> passengerDetails,@RequestParam List<SeatDetails> seatDetails){
		return ecoServ.addTicketDetails(customerDetails, ticketDetails, passengerDetails, seatDetails);
	}
	
	@PostMapping("/walletBalance")
	public WalletDetails showWalletBalanace(@RequestBody WalletAmount walletAmount)
	{
		
		return ecoServ.showWalletBalance(walletAmount.getCustomerId());
	}
	
	@PostMapping("/addBalance")
	public WalletDetails addWalletBalanace(@RequestBody UpdateWallet updateWallet)
	{
		
		return ecoServ.addWalletBalance(updateWallet.getCustomerId(),updateWallet.getWalletAmount());
	}
	
	@PostMapping("/cancelTicket")
	public StatusString cancelTicket(@RequestBody CancelTicket cancelTicket) {
		
		return ecoServ.cancelTicket(cancelTicket.getTicketNo(), cancelTicket.getEmail());
	}

	
}

