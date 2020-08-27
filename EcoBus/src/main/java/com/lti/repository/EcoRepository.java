package com.lti.repository;

import java.time.LocalDate;
import java.util.List;

import com.lti.bridge.Status;
import com.lti.bridge.StatusString;
import com.lti.model.Bus;
import com.lti.model.Customer;
import com.lti.model.Driver;
import com.lti.model.OperationalDays;
import com.lti.model.Passenger;
import com.lti.model.Routes;
import com.lti.model.Seats;
import com.lti.model.Ticket;
import com.lti.model.Transaction;
import com.sun.javafx.animation.TickCalculation;

public interface EcoRepository {
	
	//Home Functionalities
	List<Bus> searchABus(String fromCity,String toCity,String day);//Done
	List<Routes> searchRoutesByBus(List<Integer> busId,String fromCity,String toCity);
	public List<Integer> totalSeatsBooked(List<Bus> bus,LocalDate dateOfJourney);
	int registerUser(Customer customer);//Done
	int registerAgain(Customer customer,int customerId);
	boolean loginUser(String email,String password);//Done
	
	List<Ticket> fetchBookingsOfCustomer(int customerId);
	boolean adminLogin(String email,String password);
	public Customer findByEmailPassword(String email,String password);//returning object of customer
	boolean cancelTicket(int ticketId,String email);
	Ticket searchTicket(int ticketId,String email);
	
	
	//for rescheduling the bus, write one more function in service class
	boolean checkAvailibilityofBus(String day,int busid);
	boolean checkSeatsavailability(LocalDate date,int busid);
	
	
	//Forgot Password Functionalities
	boolean isValidEmail(String email);
	boolean changePassword(int customerId,String password);
	int getRegisteredCustomerId(String email);
	int checkRegisteredUser(String email);
	
	public Customer isValidCustomerId(int customerId);
	
	//DashBoard Functionalities
	boolean updatePassword(int customerId,String newPassword);//--Read about session handling
	List<Ticket> viewAllBookings(int customerId);
	Customer showProfile(int customerId);
    double showWalletBalance(int customerId);
    boolean addWalletBalance(int customerId, double amount);
    boolean updateProfile(Customer customer);
    
    
    //For ticket details
    List<Seats> getBookedSeats(int busId);    
    //Admin Functionalities 
    double getPreviousProfits(LocalDate fromDate,LocalDate toDate);//done for testing
    List<Routes> frequentlyTravelledRoutes();
    List<Customer> noReservationCustomer();//done forTesting
    boolean deleteBus(int busId);//Today done
    String mostPrefferedTypesOfBuses();
    boolean addABus(Bus bus);//Done
    boolean deleteDriver();//Today done
    public Bus findBus(int busid);
    List<Transaction> getLastMonthRecord(LocalDate date, LocalDate previousDate);
    boolean addBuswithDriver(Bus bus,Driver driver);//Done
    List<Passenger> reservationDetail(LocalDate date);
    List<Passenger> weeklyReservationDetail(LocalDate monday, LocalDate now );
    List<Passenger> monthlyReservationDetail(LocalDate start, LocalDate now);
    
    public boolean removeBus(int busId);
    
    //Reservation details of customer(Daily,Weekly&monthly)
    boolean addRoutewithBus(List<Routes> routes,int busId);//Done
    boolean addOperationalDaysWithBus(List<OperationalDays> operationalDays,int busId);//Done
    
    
    int addTicketAndPassengerWithRegisteredCustomers(Ticket ticket,List<Passenger> passenger,List<Seats> seats,Transaction transaction);//depends on the session
   // boolean addTicketAndPassengerWithUnregisteredCusomer(Ticket ticket,Passenger passenger, Customer customer);
	boolean isValidTicket(int ticketId,int customerId);
	boolean isValidTicketDate(int ticketId);
	boolean checkOldPassword(int customerId, String oldPassword);

    List<Integer> fetchNoOfSeats(int busId, LocalDate dateOfJourney) ;
	Status CancelAllTicketDetailsOfACustomer(int ticketNo);

    
	

}
