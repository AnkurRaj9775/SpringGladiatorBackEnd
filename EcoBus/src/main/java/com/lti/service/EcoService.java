package com.lti.service;



import java.time.LocalDate;
import java.util.List;

import com.lti.bridge.BusDetails;
import com.lti.bridge.LoginStatus;
import com.lti.bridge.StatusString;
import com.lti.bridge.WalletDetails;
import com.lti.bridge.PassengerDetails;
import com.lti.bridge.SeatDetails;
import com.lti.bridge.Status;
import com.lti.dto.CustomerDetails;
import com.lti.dto.TicketDetails;
import com.lti.dto.UpdateWallet;
import com.lti.model.Bus;
import com.lti.model.Customer;
import com.lti.model.Driver;
import com.lti.model.OperationalDays;
import com.lti.model.Routes;
import com.lti.model.Seats;
import com.lti.model.Ticket;


public interface EcoService {
	public Bus findBus(int busid);
	List<BusDetails> searchABus(String fromCity,String toCity,String day,LocalDate dateOfJourney);
	Status registerUser(Customer customer);
	Customer loginUser(String email,String password);
	StatusString cancelTicket(int ticketId,String email);
	Ticket searchTicket(int ticketId,String email);
	
	//Forgot Password Functionalities
	LoginStatus isValidCustomerId(int customerId);
	Status changePassword(int customerId,String password);
	
	//DashBoard Functionalities
	Status updatePassword(int customerId,String oldPassword,String newPassword);
	List<Ticket> viewAllBookings(String email);
	Customer showProfile(String email);
    WalletDetails showWalletBalance(int customerId);
    boolean updateProfile(Customer customer);
    List<Seats> getBookedSeats(int busId);
    WalletDetails addWalletBalance(int customerId, double amount);
    
    
    //Admin Functionalities 
    double getPreviousProfits(LocalDate fromDate,LocalDate toDate);
    List<Routes> frequentlyTravelledRoutes();
    boolean deleteBus(int busId);
    String mostPrefferedTypesOfBuses();
    boolean addABus(Bus bus);
    boolean deleteABus();
    boolean deleteDriver();
    boolean addADriver();
    boolean addBuswithDriver(Bus bus,Driver driver);
    //Reservation details of customer(Daily,Weekly&monthly)
    boolean addRoutewithBus(List<Routes> routes,int busId);
    boolean addOperationalDaysWithBus(List<OperationalDays> operationalDays,int busId);
    
    Status addTicketDetails(CustomerDetails customerDetails,TicketDetails ticketDetails,List<PassengerDetails> passengerDetails,List<SeatDetails> seatDetails);
    
   
    //
}
