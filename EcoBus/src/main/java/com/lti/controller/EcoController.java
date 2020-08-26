package com.lti.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.lti.bridge.SeatCountDetails;
import com.lti.bridge.Status;
import com.lti.dto.BookTicket;
import com.lti.dto.CancelTicket;
import com.lti.dto.ChangePassword;
import com.lti.dto.CustomerDetails;
import com.lti.dto.ForgotPassword;
import com.lti.dto.LoginDetails;
import com.lti.dto.PassengerDetails;
import com.lti.dto.ResetPassword;
import com.lti.dto.SearchBus;
import com.lti.dto.SeatCount;
import com.lti.dto.SeatDetails;
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
//		System.out.println(searchBus.getDay());
		System.out.println(searchBus.getDateOfJourney()+ ""+ "give date");
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		String date=searchBus.getDateOfJourney();
//		//convert String to LocalDate
//		LocalDate journeyDate = LocalDate.parse(date, formatter);
//		System.out.println(journeyDate);
		
	
        LocalDate journeyDate = LocalDate.parse(searchBus.getDateOfJourney());
    
		return ecoServ.searchABus(searchBus.getFromCity(),searchBus.getToCity(),searchBus.getDay(),journeyDate);
	}
	
	public Bus findBus(int busid){
		return ecoServ.findBus(busid);
	}
	
	@PostMapping("/bookTicket")
	public Status addTicketDetails(@RequestBody BookTicket bookTicket){
		return ecoServ.addTicketDetails(bookTicket.getCustomerDetails(), bookTicket.getTicketDetails(), bookTicket.getPassengerDetails(), bookTicket.getSeatDetails());
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

	@PostMapping("/forgotPassword")
	public LoginStatus forgotPassword(@RequestBody ForgotPassword forgotPassword)
	{
		return ecoServ.isValidCustomerId(forgotPassword.getCustomerId());
	}
	
	@PostMapping("/resetPassword")
	public Status resetPassword(@RequestBody ResetPassword resetPassword)
	{
		return ecoServ.changePassword(resetPassword.getCustomerId(), resetPassword.getPassword());
	}
	
	@PostMapping("/changePassword")
	public StatusString changePassword(@RequestBody ChangePassword changePassword)
	{
		return ecoServ.updatePassword(changePassword.getCustomerId(), changePassword.getOldPassword(), changePassword.getNewPassword());
	}

		
	
	@PostMapping("/getNoOfSeats")
	public SeatCountDetails fetchNoOfSeats(@RequestBody SeatCount seatCount)
	{
		System.out.println(seatCount.getDateOfJourney()+ "give date");
		LocalDate journeyDate = LocalDate.parse(seatCount.getDateOfJourney());
		System.out.println(ecoServ.fetchNoOfSeats(seatCount.getBusId(),journeyDate));
		return ecoServ.fetchNoOfSeats(seatCount.getBusId(),journeyDate);
	}

}

