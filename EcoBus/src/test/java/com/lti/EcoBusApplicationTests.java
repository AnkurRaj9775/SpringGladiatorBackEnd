package com.lti;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.lti.controller.EcoController;
import com.lti.dto.BookTicket;
import com.lti.dto.CustomerDetails;
import com.lti.dto.PassengerDetails;
import com.lti.dto.SeatDetails;
import com.lti.dto.TicketDetails;
import com.lti.model.Bus;
import com.lti.model.Customer;
import com.lti.model.Driver;
import com.lti.model.OperationalDays;
import com.lti.model.Routes;

@SpringBootTest
//@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
class EcoBusApplicationTests {
	@Autowired
	EcoController controller;

	Customer customer=new Customer();
	Bus bus =new Bus();
	Driver driver=new Driver();
	OperationalDays op=new OperationalDays();
	Routes ro=new Routes();
	
	@Test
	public void findBus(){
		System.out.println(controller.findBus(131104));
	}
	
	
	@Test
	public void regiterTest() {
	
		
//	?
		 
//		customer.setAge(23);
//		customer.setContact("9858789652");
//		customer.setEmail("araj3358@gmail.com");
//		customer.setGender("M");
//		customer.setName("Ankur Raj");
//		customer.setPassword("Ankur@123");
//		customer.setWalletBalance(0);
		
		customer.setAge(23);
		customer.setContact("7859145872");
		customer.setEmail("navtej552@gmail.com");
		customer.setGender("M");
		customer.setName("Navtej Singh");
		customer.setPassword("Navtej@123");
		customer.setWalletBalance(0);
//		
//		customer.setAge(23);
//		customer.setContact("8524963152");
//		customer.setEmail("yadav.neha.1225@gmail.com");
//		customer.setGender("F");
//		customer.setName("Neha Yadav");
//		customer.setPassword("Neha@123");
//		customer.setWalletBalance(0);
		
		/*
		 * customer.setAge(23); customer.setContact("9687458965");
		 * customer.setEmail("varun@gmail.com"); customer.setGender("M");
		 * customer.setName("varun"); customer.setPassword("varun@123");
		 * customer.setWalletBalance(0);
		 */
		

		System.out.println(controller.registerUser(customer));
		
	}
	
//	@Test
//	public void loginTest(){
//		System.out.println(controller.loginUser("munish.bhanawat@gmail.com","unish@123"));
//	}
//	
	@Test
	public void addAbus()
	{
//		bus.setBusName("Eagle bus");
//		bus.setBusType("AC");
//		bus.setTotalSeat(40);
//		bus.setBusPlateNumber("UK001");
		bus.setBusName("Jaguar bus");
		bus.setBusType("AC");
		bus.setTotalSeat(25);
		bus.setBusPlateNumber("UK002");
		System.out.println(controller.addAbus(bus));
	}
	
	@Test
	public void addBuswithDriver()
	{
//		bus.setBusName("Tiger bus");
//		bus.setBusType("AC");
//		bus.setTotalSeat(20);
//		bus.setBusPlateNumber("DL003");
//		driver.setContact("9414785958");
//		driver.setDriverName("John");
		
//		bus.setBusName("Elephent bus");
//		bus.setBusType("Non-AC");
//		bus.setTotalSeat(40);
//		bus.setBusPlateNumber("DL004");
//		driver.setContact("9414968274");
//		driver.setDriverName("Majinder");
//		
		bus.setBusName("Blue Whale bus");
		bus.setBusType("AC");
		bus.setTotalSeat(20);
		bus.setBusPlateNumber("DL005");
		driver.setContact("9414789547");
		driver.setDriverName("Raman");
//		
		
		
		System.out.println(controller.addBuswithDriver(bus, driver));
	}

	@Test
	public void addOperationalDaysWithBus()
	{
//		List<OperationalDays> operationalDays=new ArrayList<OperationalDays>();
//		op=new OperationalDays();
//		op.setOperationalDays("Monday");
//		operationalDays.add(op);
//		
//		op=new OperationalDays();
//		op.setOperationalDays("Thursday");
//		operationalDays.add(op);
//		
//		op=new OperationalDays();
//		op.setOperationalDays("Saturday");
//		operationalDays.add(op);
//		controller.addOperationalDaysWithBus(operationalDays, 131122);
		
//		op=new OperationalDays();
//		op.setOperationalDays("Tuesday");
//		operationalDays.add(op);
//		
//		op=new OperationalDays();
//		op.setOperationalDays("Friday");
//		operationalDays.add(op);
//		
//		op=new OperationalDays();
//		op.setOperationalDays("Sunday");
//		operationalDays.add(op);
//		controller.addOperationalDaysWithBus(operationalDays, 131103);
		
//		
//		op=new OperationalDays();
//		op.setOperationalDays("Monday");
//		operationalDays.add(op);
//		op=new OperationalDays();
//		op.setOperationalDays("Tuesday");
//		operationalDays.add(op);
//		op=new OperationalDays();
//		op.setOperationalDays("Wednesday");
//		operationalDays.add(op);
//		
//		op=new OperationalDays();
//		op.setOperationalDays("Thursday");
//		operationalDays.add(op);
//		op=new OperationalDays();
//		op.setOperationalDays("Friday");
//		operationalDays.add(op);
//		
//		op=new OperationalDays();
//		op.setOperationalDays("Saturday");
//		operationalDays.add(op);
//		op=new OperationalDays();
//		op.setOperationalDays("Sunday");
//		operationalDays.add(op);
//		controller.addOperationalDaysWithBus(operationalDays, 131104);
		
	}
	
	@Test
	public void addRoutewithBus(){
		List<Routes> routes=new ArrayList<Routes>();
//		ro=new Routes();
//		ro.setFromCity("Delhi");
//		ro.setToCity("Dehradun");
//		ro.setDepartureTime("18:00");
//		ro.setArrivalTime("23:00");
//		ro.setFare(550);
//		ro.setDuration("05:00");
//		routes.add(ro);
//		
//		ro=new Routes();
//		ro.setFromCity("Delhi");
//		ro.setToCity("Saharanpur");
//		ro.setDepartureTime("18:00");
//		ro.setArrivalTime("21:00");
//		ro.setFare(400);
//		ro.setDuration("03:00");
//		routes.add(ro);
//		
//		ro=new Routes();
//		ro.setFromCity("Saharanpur");
//		ro.setToCity("Dehradun");
//		ro.setDepartureTime("21:00");
//		ro.setArrivalTime("23:00");
//		ro.setFare(220);
//		ro.setDuration("03:00");
//		routes.add(ro);
//		
//		controller.addRoutewithBus(routes, 131102);
		
		ro=new Routes();
		ro.setFromCity("Delhi");
		ro.setToCity("Saharanpur");
		ro.setDepartureTime("16:00");
		ro.setArrivalTime("20:00");
		ro.setFare(300);
		ro.setDuration("04:00");
		routes.add(ro);
		
		ro=new Routes();
		ro.setFromCity("Delhi");
		ro.setToCity("Meerut");
		ro.setDepartureTime("16:00");
		ro.setArrivalTime("18:30");
		ro.setFare(150);
		ro.setDuration("02:30");
		routes.add(ro);
		
		ro=new Routes();
		ro.setFromCity("Meerut");
		ro.setToCity("Saharanpur");
		ro.setDepartureTime("18:40");
		ro.setArrivalTime("20:00");
		ro.setFare(70);
		ro.setDuration("01:20");
		routes.add(ro);
		
		controller.addRoutewithBus(routes, 131104);
	}
	
	
	
//	@Test
//	public void searchBus(){
//		System.out.println(controller.searchABus("Delhi", "Saharanpur", "Tuesday","23-08-2020"));
//	}
//	
	TicketDetails ticketDetails =new TicketDetails();
	CustomerDetails customerDetails= new CustomerDetails();
	
	PassengerDetails p=new PassengerDetails();
	SeatDetails s=new SeatDetails();
	
	@Test
	public void addticketwithdetails(){
		List<PassengerDetails> passengerDetails=new ArrayList<PassengerDetails>();
		List<SeatDetails> seatDetails=new ArrayList<SeatDetails>();
		ticketDetails.setBusId(131102);
		ticketDetails.setDateOfBooking(LocalDate.now());
		ticketDetails.setDateOfJourney(LocalDate.of(2020, 9, 3));
		ticketDetails.setNoOfSeatsBooked(2);
		ticketDetails.setTotalCost(1100);
		
		customerDetails.setContact("9858789652");
		customerDetails.setEmail("araj3358@gmail.com");
		p=new PassengerDetails();
		p.setAge(23);
		p.setGender("M");
		p.setName("Munish");
		passengerDetails.add(p);
		
		
		p=new PassengerDetails();
		p.setAge(23);
		p.setGender("M");
		p.setName("Navtej");
		passengerDetails.add(p);
		
		s=new SeatDetails();
		s.setSeatNo(5);
		seatDetails.add(s);
		
		s=new SeatDetails();
		s.setSeatNo(6);
		seatDetails.add(s);
		
		System.out.println(passengerDetails.toString());
		System.out.println(seatDetails.toString());
		System.out.println(seatDetails.size() +" "+passengerDetails.size());
//		System.out.println(controller.addTicketDetails(customerDetails, ticketDetails, passengerDetails, seatDetails));
	
	
	}
	
	@Test
	public void bookMyTicket()
	{
		BookTicket bookTicket=new BookTicket();
		customerDetails.setContact("9858789652");
		customerDetails.setEmail("amit123@gmail.com");
		
		bookTicket.setCustomerDetails(customerDetails);
		
		ticketDetails.setBusId(131102);
		ticketDetails.setDateOfBooking(LocalDate.now());
		ticketDetails.setDateOfJourney(LocalDate.of(2020, 8, 23));
		ticketDetails.setNoOfSeatsBooked(2);
		ticketDetails.setTotalCost(1100);
		ticketDetails.setFromCity("Delhi");
		ticketDetails.setToCity("Saharanpur");
		bookTicket.setTicketDetails(ticketDetails);
		
		List<PassengerDetails> passengerDetails=new ArrayList<PassengerDetails>();
		List<SeatDetails> seatDetails=new ArrayList<SeatDetails>();
		
		p=new PassengerDetails();
		p.setAge(23);
		p.setGender("M");
		p.setName("Munish");
		passengerDetails.add(p);
		
		
		p=new PassengerDetails();
		p.setAge(23);
		p.setGender("M");
		p.setName("Navtej");
		passengerDetails.add(p);
		
		bookTicket.setPassengerDetails(passengerDetails);
		
		s=new SeatDetails();
		s.setSeatNo(5);
		seatDetails.add(s);
		
		s=new SeatDetails();
		s.setSeatNo(6);
		seatDetails.add(s);
		
		bookTicket.setSeatDetails(seatDetails);
		
		controller.addTicketDetails(bookTicket);
	}

}
