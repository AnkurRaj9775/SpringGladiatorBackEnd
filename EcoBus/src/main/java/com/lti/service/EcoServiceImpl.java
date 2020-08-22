package com.lti.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lti.bridge.BusDetails;
import com.lti.bridge.PassengerDetails;
import com.lti.bridge.SeatDetails;
import com.lti.bridge.Status;
import com.lti.dto.CustomerDetails;
import com.lti.dto.TicketDetails;
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

@Service
public class EcoServiceImpl implements EcoService {

	@Autowired
	EcoRepository ecoRep;

	Customer cust=new Customer();
	Status status= new Status();
	Bus bus=new Bus();
	public Status registerUser(Customer customer) {
		status = new Status();
		if (ecoRep.checkRegisteredUser(customer.getEmail())) {
			status.setResultStatus(false);
			return status;
		}
		int check = ecoRep.registerUser(customer);
		if (check > 0) {
			status.setResultStatus(true);
		} else
			status.setResultStatus(false);
		return status;

	}

	public Customer loginUser(String email, String password) {
		try {
		 if(!ecoRep.loginUser(email, password))
		 {
			 throw new EcoServiceException("Customer is not registered");
		 }
		 Customer customer=ecoRep.findByEmailPassword(email, password);
			return customer;
		}
		catch(EmptyResultDataAccessException e)
		{
			throw new EcoServiceException("Incorect email/password");
		}
	}

	public boolean addABus(Bus bus) {

		return ecoRep.addABus(bus);
	}

	public boolean cancelTicket(int ticketId, String email) {
		// TODO Auto-generated method stub
		return false;
	}

	public Ticket searchTicket(int ticketId, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isValidEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean changePassword(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updatePassword(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Ticket> viewAllBookings(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public Customer showProfile(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public double showWalletBalance(String email) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean updateProfile(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Seats> getBookedSeats(int busId) {
		// TODO Auto-generated method stub
		return null;
	}

	public double getPreviousProfits(LocalDate fromDate, LocalDate toDate) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Routes> frequentlyTravelledRoutes() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteBus(int busId) {
		// TODO Auto-generated method stub
		return false;
	}

	public String mostPrefferedTypesOfBuses() {
		// TODO Auto-generated method stub
		return null;
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

	public List<BusDetails> searchABus(String fromCity, String toCity, String day,LocalDate dateOfJourney) {
		List<Bus> busDetails=ecoRep.searchABus(fromCity, toCity, day);
		List<Integer> busId=new ArrayList<>();
		
		for(int i=0;i<busDetails.size();i++) {
			
			busId.add(busDetails.get(i).getBusId());
		}
		List<Routes> routesDetails=ecoRep.searchRoutesByBus(busId, fromCity, toCity);
		
		List<Integer> totalSeat=ecoRep.totalSeatsBooked(busDetails, dateOfJourney);
		
		List<BusDetails> finalBusDetails=new ArrayList<>();
		
		for(int i=0;i<busDetails.size();i++)
		{
			BusDetails b=new BusDetails();
			b.setResultStatus(true);
			b.setBusId(busDetails.get(i).getBusId());
			b.setBusName(busDetails.get(i).getBusName());
			b.setBusType(busDetails.get(i).getBusType());
			b.setDepartureTime(routesDetails.get(i).getDepartureTime());
			b.setArrivalTime(routesDetails.get(i).getArrivalTime());
			b.setFare(routesDetails.get(i).getFare());
			b.setTotalSeatsAvailable(totalSeat.get(i));
			b.setDuration(routesDetails.get(i).getDuration());
			finalBusDetails.add(b);
			System.out.println(finalBusDetails);
		}
		//return ecoRep.searchABus(fromCity, toCity, day);
		return finalBusDetails;
	}

	public Bus findBus(int busid) {
		// TODO Auto-generated method stub
		return ecoRep.findBus(busid);
	}
//==================================================================
	Ticket ticket=new Ticket();
	
	Customer customer=new Customer();
	Transaction transaction=new Transaction();
	public Status addTicketDetails(CustomerDetails customerDetails, TicketDetails ticketDetails,
			List<PassengerDetails> passengerDetails, List<SeatDetails> seatDetails) {
		int custId = 0;
		if (!ecoRep.isValidEmail(customerDetails.getEmail())) {
			cust.setEmail(customerDetails.getEmail());
			cust.setContact(customerDetails.getContact());
			System.out.println("Inside If");
			custId = ecoRep.registerUser(cust);
		} else{
			System.out.println("Inside else");
			custId = ecoRep.getRegisteredCustomerId(customerDetails.getEmail());
		}
		System.out.println(custId);
		List<Seats> seats = new ArrayList<Seats>();
		List<Passenger> passenger = new ArrayList<Passenger>();
		Passenger ptemp =new Passenger();
		Seats stemp = new Seats();
		System.out.println(ticketDetails.getBusId());
		bus.setBusId(ticketDetails.getBusId());
		for (PassengerDetails p : passengerDetails) {

			ptemp.setAge(p.getAge());
			ptemp.setGender(p.getGender());
			ptemp.setName(p.getName());
			passenger.add(ptemp);

		}
		for (int i = 0; i < seatDetails.size(); i++) {

			stemp.setBus(bus);
			stemp.setDate(ticketDetails.getDateOfJourney());
			stemp.setGender(passengerDetails.get(i).getGender());
			stemp.setSeats(seatDetails.get(i).getSeatNo());
			seats.add(stemp);
		}
		System.out.println(seats.size() +" "+passenger.size());
		
		transaction.setAmount(ticketDetails.getTotalCost());
		transaction.setTransactionDate(ticketDetails.getDateOfBooking());

		ticket.setBus(bus);

		customer.setCustomerId(custId);
		ticket.setCustomer(customer);

		ticket.setDateOfBooking(ticketDetails.getDateOfBooking());
		ticket.setDateOfJourney(ticketDetails.getDateOfJourney());
		ticket.setNoOfSeatsBooked(ticketDetails.getNoOfSeatsBooked());
		ticket.setTotalCost(ticketDetails.getTotalCost());
		// ticket.setPassenger(passenger);

		if (ecoRep.addTicketAndPassengerWithRegisteredCustomers(ticket, passenger, seats,transaction)) {
			status.setResultStatus(true);
			return status;
		}
		status.setResultStatus(false);
		return status;
	}

	

}
