package com.lti.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lti.bridge.BusDetails;
import com.lti.bridge.LoginStatus;
import com.lti.bridge.MyBookTickets;
import com.lti.bridge.MyBookingDetails;
import com.lti.bridge.RegisterStatus;
import com.lti.bridge.StatusString;




import com.lti.bridge.TransactionDetailsForRecord;

import com.lti.bridge.ViewProfile;
import com.lti.bridge.WalletDetails;
import com.lti.bridge.SeatCountDetails;
import com.lti.bridge.Status;
import com.lti.dto.CancelTicketUpdation;
import com.lti.dto.CustomerDetails;
import com.lti.dto.PassengerDetails;

import com.lti.dto.BookingSeatDetails;

import com.lti.dto.TicketDetails;
import com.lti.dto.UpdateWallet;
import com.lti.email.Email;
import com.lti.exception.EcoServiceException;
import com.lti.model.Bus;
import com.lti.model.Customer;
import com.lti.model.Driver;
import com.lti.model.OperationalDays;
import com.lti.model.Passenger;
import com.lti.model.Routes;
import com.lti.model.Seats;
import com.lti.model.Ticket;
import com.lti.model.Transaction;
import com.lti.repository.EcoRepository;
import static java.time.temporal.TemporalAdjusters.*;

@Service
public class EcoServiceImpl implements EcoService {

	@Autowired
	EcoRepository ecoRep;

	@Autowired
	Email email;

	Customer cust = new Customer();
	Status status = new Status();
	Bus bus = new Bus();

	public RegisterStatus registerUser(Customer customer) {
		RegisterStatus status = new RegisterStatus();
		int customerId = ecoRep.checkRegisteredUser(customer.getEmail());
		if (customerId < 0) {
			
			status.setMessage("This email is already registered with us!");
			return status;
		} else if (customerId > 0) {
			int check1 = ecoRep.registerAgain(customer, customerId);
			if (check1 > 0) {
				email.registerEmail(customer.getEmail(), customer.getName(), check1);
				status.setMessage("Registered  Successfully !!!.Please check Your Mail.");
			}
			else {
				status.setMessage("This email is already registered with us!");
			}

		} else {
			int check2 = ecoRep.registerUser(customer);
			if (check2 > 0) {

				email.registerEmail(customer.getEmail(), customer.getName(), check2);
				status.setMessage("Registered  Successfully !!!.Please check Your Mail.");
			} else
				status.setMessage("This email is already registered with us!");
		}
		return status;

	}

	public Customer loginUser(String email, String password) {
		try {
			if (!ecoRep.loginUser(email, password)) {
				throw new EcoServiceException("Customer is not registered");
			}
			Customer customer = ecoRep.findByEmailPassword(email, password);
			return customer;
		} catch (EmptyResultDataAccessException e) {
			throw new EcoServiceException("Incorect email/password");
		}
	}

	public Status adminLogin(String email,String password) {
		
		if(ecoRep.adminLogin(email, password))
		{
			status.setResultStatus(true);
			return status;
		}
		status.setResultStatus(false);
		return status;
	}
	public boolean addABus(Bus bus) {

		return ecoRep.addABus(bus);
	}

	public StatusString cancelTicket(int ticketId, String email) {

		int id = 0;
		StatusString cancelTicketDetails = new StatusString();
		if (!ecoRep.isValidEmail(email)) {
			cancelTicketDetails.setStatus("Please enter correct email id");
			return cancelTicketDetails;
		} else if (ecoRep.checkRegisteredUser(email)>0) {
			id = ecoRep.getRegisteredCustomerId(email);
			if (ecoRep.isValidTicket(ticketId, id) && ecoRep.isValidTicketDate(ticketId)) {

				cancelTicketDetails.setStatus("Please Login to Cancel the Ticket.");
				return cancelTicketDetails;
			} else if (!ecoRep.isValidTicket(ticketId, id)) {
				cancelTicketDetails.setStatus("Cannot Fetch Ticket details at the moment.");
				return cancelTicketDetails;

			} else {
				cancelTicketDetails
						.setStatus("This ticket can not be cancelled as the date of journey has already passed.");
				return cancelTicketDetails;
			}
		}
		cancelTicketDetails.setStatus("Please Register And Login to Cancel the Ticket.");
		return cancelTicketDetails;

	}

	public Ticket searchTicket(int ticketId, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public LoginStatus isValidCustomerId(int customerId) {
		Customer customer = ecoRep.isValidCustomerId(customerId);
		LoginStatus loginStatus = new LoginStatus();
		if (customer != null) {
			loginStatus.setCustomerId(customer.getCustomerId());
			loginStatus.setResultStatus(true);
			return loginStatus;
		}

		loginStatus.setResultStatus(false);
		return loginStatus;
	}

	public Status changePassword(int customerId, String password) {
		// TODO Auto-generated method stub

		Customer customer = ecoRep.isValidCustomerId(customerId);
		if (customer != null) {
			ecoRep.changePassword(customerId, password);
			status.setResultStatus(true);
			return status;
		}
		status.setResultStatus(false);
		return status;
	}

	public double getPreviousProfits() {
		LocalDate toDate = LocalDate.now();
		LocalDate fromDate = toDate.minusMonths(1);
		return ecoRep.getPreviousProfits(fromDate, toDate);
	}

	public StatusString updatePassword(int customerId, String oldPassword, String newPassword) {
		StatusString status = new StatusString();
		if (!ecoRep.checkOldPassword(customerId, oldPassword)) {
			status.setStatus("Please enter correct old password");
			return status;
		}
		if (oldPassword.equals(newPassword)) {
			status.setStatus("Old and new password cannot be same!!");
			return status;
		}
		if (ecoRep.updatePassword(customerId, newPassword)) {
			status.setStatus("Password Updated Successfully !!");
			return status;
		}
		status.setStatus("Could not update Password");
		return status;

	}
//
//	public List<Ticket> viewAllBookings(int customerId) {
//
//		Ticket ticket = new Ticket();
//		List<Ticket> ticketList = new ArrayList<>();
//		Bus bus = new Bus();
//		MyBookings ticketsDetail = new MyBookings();
//
//		ticketList = ecoRep.viewAllBookings(customerId);
//		ticketsDetail.setBusId(bus.getBusId());
//		ticketsDetail.setBusName(bus.getBusName());
//		ticketsDetail.setTicketId(ticket.getTicketId());
//		ticketsDetail.setNoOfSeatsBooked(ticket.getNoOfSeatsBooked());
//		ticketsDetail.setDateOfJourney(ticket.getDateOfJourney().toString());
//		ticketsDetail.setDateOfBooking(ticket.getDateOfBooking().toString());
//		ticketsDetail.setFromCity(ticket.getFromCity());
//		ticketsDetail.setToCity(ticket.getToCity());
//		ticketsDetail.setTotalCost(ticket.getTotalCost());
//
//		return ticketList;
//
//	}

	public ViewProfile showProfile(int customerId) {

		Customer customer = new Customer();
		ViewProfile viewProfile = new ViewProfile();
		customer = ecoRep.showProfile(customerId);
		viewProfile.setName(customer.getName());
		viewProfile.setAge(customer.getAge());
		viewProfile.setGender(customer.getGender());
		viewProfile.setEmail(customer.getEmail());
		viewProfile.setContact(customer.getContact());

		return viewProfile;
	}

	public WalletDetails showWalletBalance(int customerId) {

		WalletDetails walletAmount = new WalletDetails();
		walletAmount.setAmount(ecoRep.showWalletBalance(customerId));
		return walletAmount;

	}

	public WalletDetails addWalletBalance(int custId, double amount) {

		WalletDetails wallet = new WalletDetails();
		if (ecoRep.addWalletBalance(custId, amount)) {
			wallet.setStatus("Amount added successfully!");
			wallet.setAmount(ecoRep.showWalletBalance(custId));
		} else {
			wallet.setStatus("Oops! Could not add amount");
			wallet.setAmount(ecoRep.showWalletBalance(custId));
		}
		return wallet;
	}

	public boolean updateProfile(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Seats> getBookedSeats(int busId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Routes> frequentlyTravelledRoutes() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteBus(int busId) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteABus() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteDriver() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addADriver() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addBuswithDriver(Bus bus, Driver driver) {
		// TODO Auto-generated method stub
		return ecoRep.addBuswithDriver(bus, driver);
	}

	public boolean addRoutewithBus(List<Routes> routes, int busId) {
		// TODO Auto-generated method stub
		return ecoRep.addRoutewithBus(routes, busId);
	}

	public boolean addOperationalDaysWithBus(List<OperationalDays> operationalDays, int busId) {
		// TODO Auto-generated method stub
		return ecoRep.addOperationalDaysWithBus(operationalDays, busId);
	}

	public List<BusDetails> searchABus(String fromCity, String toCity, String day, LocalDate dateOfJourney) {
		System.out.println(dateOfJourney);
		List<Bus> busDetails = ecoRep.searchABus(fromCity, toCity, day);
		List<Integer> busId = new ArrayList<>();

		for (int i = 0; i < busDetails.size(); i++) {

			busId.add(busDetails.get(i).getBusId());
		}
		List<Routes> routesDetails = ecoRep.searchRoutesByBus(busId, fromCity, toCity);

		List<Integer> totalSeat = ecoRep.totalSeatsBooked(busDetails, dateOfJourney);

		List<BusDetails> finalBusDetails = new ArrayList<>();

		for (int i = 0; i < busDetails.size(); i++) {
			BusDetails b = new BusDetails();
			b.setResultStatus(true);
			b.setBusId(busDetails.get(i).getBusId());
			b.setBusName(busDetails.get(i).getBusName());
			b.setBusType(busDetails.get(i).getBusType());
			b.setDepartureTime(routesDetails.get(i).getDepartureTime());
			b.setArrivalTime(routesDetails.get(i).getArrivalTime());
			b.setFare(routesDetails.get(i).getFare());
			b.setTotalSeatsAvailable(totalSeat.get(i));
			b.setDuration(routesDetails.get(i).getDuration());
			b.setTotalSeats(busDetails.get(i).getTotalSeat());
			finalBusDetails.add(b);
			System.out.println(finalBusDetails);
		}
		// return ecoRep.searchABus(fromCity, toCity, day);
		return finalBusDetails;
	}

	@Override
	public List<Customer> noReservationCustomer() {
		List<Customer> customer = ecoRep.noReservationCustomer();
		return customer;

	}

	@Override
	public List<TransactionDetailsForRecord> getPreviousTransaction() {
		
		LocalDate date = LocalDate.now();		
		List<TransactionDetailsForRecord>  transactionRecord = new ArrayList<>();
	    LocalDate previousDate = date.minusMonths(1); 
		System.out.println(date+"current date");
		System.out.println(previousDate+"previous month date");
		List<Transaction> t = ecoRep.getLastMonthRecord(previousDate,date);		
		for(int i =0;i<t.size();i++) {
			TransactionDetailsForRecord  transRecord= new TransactionDetailsForRecord();
			transRecord.setTicketId(t.get(i).getTicket().getTicketId());
			transRecord.setAmount(t.get(i).getAmount());
			transRecord.setTransactionDate(t.get(i).getTransactionDate());
			transRecord.setTransactionId(t.get(i).getTransactionId());
			transactionRecord.add(transRecord);
		}
		return transactionRecord;
	}

	@Override
	public List<Passenger> reservationDetails() {
		return ecoRep.reservationDetail(LocalDate.now());
	}

	@Override
	public List<Passenger> weeklyReservationDetails() {

		LocalDate monday = LocalDate.now();
		while (monday.getDayOfWeek() != DayOfWeek.MONDAY) {
			monday = monday.minusDays(1);
		}
		System.out.println(monday);
		return ecoRep.weeklyReservationDetail(monday, LocalDate.now());
	}

	@Override
	public List<Passenger> monthlyReservationDetails() {

		LocalDate now = LocalDate.now();
		LocalDate start = now.with(firstDayOfMonth());
		return ecoRep.monthlyReservationDetail(start, LocalDate.now());

	}

	@Override
	public String mostPrefferedTypesOfBuses() {

		return ecoRep.mostPrefferedTypesOfBuses();
	}

	public Bus findBus(int busid) {
		// TODO Auto-generated method stub
		return ecoRep.findBus(busid);
	}

//==================================================================
	Ticket ticket = new Ticket();

	Customer customer = new Customer();
	Transaction transaction = new Transaction();

	public Status addTicketDetails(CustomerDetails customerDetails, TicketDetails ticketDetails,
			List<PassengerDetails> passengerDetails, List<BookingSeatDetails> seatDetails) {
		int custId = 0;
		System.out.println(customerDetails.getEmail());
		if (!ecoRep.isValidEmail(customerDetails.getEmail())) {
			cust.setEmail(customerDetails.getEmail());
			cust.setContact(customerDetails.getContact());

			custId = ecoRep.registerUser(cust);
		} else {
			System.out.println("Inside else");
			custId = ecoRep.getRegisteredCustomerId(customerDetails.getEmail());
		}

		List<Seats> seats = new ArrayList<Seats>();
		List<Passenger> passenger = new ArrayList<Passenger>();

		bus.setBusId(ticketDetails.getBusId());
		for (PassengerDetails p : passengerDetails) {
			Passenger ptemp = new Passenger();
			ptemp.setAge(p.getAge());
			ptemp.setGender(p.getGender());
			ptemp.setName(p.getName());
			passenger.add(ptemp);

		}
		for (int i = 0; i < seatDetails.size(); i++) {
			Seats stemp = new Seats();
			stemp.setBus(bus);
			stemp.setDate(ticketDetails.getDateOfJourney());
			stemp.setGender(passengerDetails.get(i).getGender());
			stemp.setSeats(seatDetails.get(i).getSeatNo());
			seats.add(stemp);
		}

		transaction.setAmount(ticketDetails.getTotalCost());
		transaction.setTransactionDate(ticketDetails.getDateOfBooking());

		ticket.setBus(bus);

		customer.setCustomerId(custId);
		ticket.setCustomer(customer);

		ticket.setDateOfBooking(ticketDetails.getDateOfBooking());
		ticket.setDateOfJourney(ticketDetails.getDateOfJourney());
		ticket.setNoOfSeatsBooked(ticketDetails.getNoOfSeatsBooked());
		ticket.setTotalCost(ticketDetails.getTotalCost());
		ticket.setFromCity(ticketDetails.getFromCity());
		ticket.setToCity(ticketDetails.getToCity());
		// ticket.setPassenger(passenger);
		int ticketId = ecoRep.addTicketAndPassengerWithRegisteredCustomers(ticket, passenger, seats, transaction);
		if (ticketId > 0) {
			email.ticketDetailsEmail(customerDetails, ticketDetails, ticketId);
			status.setResultStatus(true);
			return status;

		}
		status.setResultStatus(false);
		return status;
	}

	@Override
	public SeatCountDetails fetchNoOfSeats(int busId, LocalDate dateOfJourney) {
		List<Integer> noOfSeats = new ArrayList<>();
		noOfSeats = ecoRep.fetchNoOfSeats(busId, dateOfJourney);
		SeatCountDetails seatCountDetails = new SeatCountDetails();
		seatCountDetails.setNoOfseats(noOfSeats);
		System.out.println(noOfSeats.toString());
		return seatCountDetails;
	}

	@Override
	public MyBookingDetails fetchBookingsOfCustomer(int customerId) {
		List<Ticket> myBookings=ecoRep.fetchBookingsOfCustomer(customerId);
		List<MyBookTickets> myBookTickets=new ArrayList<>();
		MyBookingDetails myBookingDetails=new MyBookingDetails();
		if(myBookings.size()>0)
		{
			for(int i=0;i<myBookings.size();i++)
			{
				
				MyBookTickets mbt=new MyBookTickets();
				mbt.setToCity(myBookings.get(i).getToCity());
				mbt.setFromCity(myBookings.get(i).getFromCity());
				mbt.setDateOfBooking(myBookings.get(i).getDateOfBooking());
				mbt.setDateOfJourney(myBookings.get(i).getDateOfJourney());
				mbt.setNoOfSeatsBooked(myBookings.get(i).getNoOfSeatsBooked());
				mbt.setTotalCost(myBookings.get(i).getTotalCost());
				mbt.setTicketId(myBookings.get(i).getTicketId());
				if(myBookings.get(i).getDateOfJourney().minusDays(2).compareTo(LocalDate.now())>0)
				{
					mbt.setCancelButton(true);
			
				}
				else {
					mbt.setCancelButton(false);
				}
				myBookTickets.add(mbt);
			}
			myBookingDetails.setMybookings(myBookTickets);
			myBookingDetails.setResultStatus(true);
			return myBookingDetails;
		}
		
		myBookingDetails.setResultStatus(false);
		return myBookingDetails;
	
	}
	public Status CancelAllTicketDetailsOfACustomer(CancelTicketUpdation cancelTicketUpdation) {
		
		return ecoRep.CancelAllTicketDetailsOfACustomer(cancelTicketUpdation.getTicketNo());

	}

	@Override
	public Status removeBus(int busId) {
		if(ecoRep.removeBus(busId))
		{
		status.setResultStatus(true);
		return status;
		}
		status.setResultStatus(false);
		return status;
	}

}
