 package com.lti.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.bridge.StatusString;
import com.lti.model.Admin;
import com.lti.model.Bus;
import com.lti.model.Customer;
import com.lti.model.Driver;
import com.lti.model.OperationalDays;
import com.lti.model.Passenger;
import com.lti.model.Routes;
import com.lti.model.Seats;
import com.lti.model.Ticket;
import com.lti.model.Transaction;

@Repository
public class EcoRepositoryImpl implements EcoRepository {

	@PersistenceContext
	EntityManager em;

	@Transactional
	public int registerUser(Customer customer) {
		int cust_id = 0;
		if (customer != null) {

			Customer c = em.merge(customer);
			cust_id = c.getCustomerId();

		}
		// System.out.println(cust_id);
		return cust_id;
	}

	@Override
	@Transactional
	public int registerAgain(Customer customer, int customerId) {
		Customer c=new Customer();
		c=em.find(Customer.class, customerId);
		c.setAge(customer.getAge());
		c.setContact(customer.getContact());
		c.setGender(customer.getGender());
		c.setName(customer.getName());
		c.setPassword(customer.getPassword());
		try {
		c=em.merge(c);
		}catch (NoResultException e) {
			// TODO: handle exception
		}
		
		return c.getCustomerId();
	}
	public boolean loginUser(String email, String password) {
		String sql = "select cs from Customer cs where cs.email= :email and cs.password = :password";
		TypedQuery<Customer> qry = em.createQuery(sql, Customer.class);
		qry.setParameter("email", email);
		qry.setParameter("password", password);
		List<Customer> customer = qry.getResultList();
		if (customer.isEmpty())
			return false;

		return true;
	}

	
	public boolean adminLogin(String email,String password) {
		
		String sql = "select ad from Admin ad where ad.userName= :email and ad.password = :password";
		TypedQuery<Admin> qry = em.createQuery(sql, Admin.class);
		qry.setParameter("email", email);
		qry.setParameter("password", password);
		List<Admin> admin = qry.getResultList();
		if (admin.isEmpty())
			return false;

		return true;
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
		System.out.println(email);
		String sql = "select cs from Customer cs where cs.email= :email";
		TypedQuery<Customer> qry = em.createQuery(sql, Customer.class);
		qry.setParameter("email", email);
		List<Customer> customer = new ArrayList<>();
		try {
			customer = qry.getResultList();
		} catch (NoResultException e) {

		}
		System.out.println(customer);
		if (customer.isEmpty())
			return false;

		return true;
	}

	/*
	 * public boolean isValidEmail(String email) { System.out.println(email);
	 * 
	 * String sql = "select cs.email from Customer cs where cs.email= :email"; Query
	 * qry = em.createQuery(sql); qry.setParameter("email", email); String
	 * tempemail=null; try { tempemail = (String) qry.getSingleResult(); }
	 * catch(NoResultException e) {
	 * 
	 * } System.out.println(tempemail); if (tempemail==null) return false;
	 * 
	 * return true; }
	 */
	@Transactional
	public boolean changePassword(int customerId, String password) {
	
		Customer cust = new Customer();
		cust=em.find(Customer.class, customerId);
		cust.setPassword(password);
		em.merge(cust);
		return true;
	}

	@Transactional
	public boolean updatePassword(int customerId,String newPassword) {
		Customer cust = new Customer();
		
		cust=em.find(Customer.class, customerId);
		cust.setPassword(newPassword);
		
		try {
			cust=em.merge(cust);
		}
		catch(NoResultException ne) {
			
		}
		if(cust.getCustomerId()>0) {
			return true;
		}
		return false;
	}

	public List<Ticket> viewAllBookings(int customerId) {
		
		String sql = "select ti from Ticket ti where ti.customer.customerId= :customerId";
		TypedQuery<Ticket> qry = em.createQuery(sql, Ticket.class);
		qry.setParameter("customerId", customerId);
		List<Ticket> ticket=new ArrayList<>();
		try {
			ticket=qry.getResultList();
		}
		catch(NoResultException nre){
			
		}
		return ticket;
		
	}

	public Customer showProfile(int customerId) {
		
		String sql = "select cs from Customer cs where cs.customerId= :customerId";
		TypedQuery<Customer> qry = em.createQuery(sql, Customer.class);
		qry.setParameter("customerId", customerId);
		Customer cust = new Customer();
		try {
			cust=qry.getSingleResult();
		}
		catch(NoResultException nre){
			
		}
		return cust;
	}
	
	public double showWalletBalance(int customerId) {
		
		String sql = "select cs from Customer cs where cs.customerId= :customerId";
		TypedQuery<Customer> qry = em.createQuery(sql, Customer.class);
		qry.setParameter("customerId", customerId);
		Customer cust = new Customer();
		try {
			cust = qry.getSingleResult();
			
		} catch (NoResultException e) {

		}
		
		return cust.getWalletBalance();
	}
	
	@Transactional
	public boolean addWalletBalance(int customerId, double amount) {
		Customer cust = new Customer();
		cust=em.find(Customer.class, customerId);
		cust.setWalletBalance(cust.getWalletBalance()+amount);
	
		Customer customer=em.merge(cust);
		
		if(customer.getWalletBalance()>0)
			return true;
		
		
			return false;
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
		
		return null;
	}

	public boolean deleteBus(int busId) {
		
		return false;
	}

	

	@Transactional
	public boolean addABus(Bus bus) {
		if (bus != null) {
			em.persist(bus);
			return true;
		}
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

	@Transactional
	public boolean addBuswithDriver(Bus bus, Driver driver) {

		if (bus != null && driver != null) {
			driver.setBus(bus);
			bus.setDriver(driver);
			em.merge(driver);
			return true;
		}
		return false;

	}

	@Transactional
	public boolean addRoutewithBus(List<Routes> routes, int busId) {

		if (routes.size() > 0) {
			Bus bus = em.find(Bus.class, busId);
			bus.setRoutes(routes);
			for (Routes r : routes) {
				r.setBus(bus);

			}
			em.merge(bus);
			return true;
		}
		return false;
	}

	@Transactional
	public boolean addOperationalDaysWithBus(List<OperationalDays> operationalDays, int busId) {
		if (operationalDays.size() > 0) {
			Bus bus = em.find(Bus.class, busId);
			bus.setOperationalDays(operationalDays);
			for (OperationalDays op : operationalDays) {
				op.setBus(bus);

			}
			em.merge(bus);
			return true;
		}
		return false;

	}

//	public List<Bus> searchABus(String fromCity, String toCity, String day) {
//		String sql = "select bs from Bus bs where bs.busId in"
//				+ "(select r.bus from Routes r Join OperationalDays o ON r.fromCity=:from "
//				+ "and r.toCity=:to and o.operationalDays=:day )";
//		TypedQuery<Bus> query = em.createQuery(sql, Bus.class);
//		query.setParameter("from", fromCity);
//		query.setParameter("to", toCity);
//		query.setParameter("day", day);
//		List<Bus> bus = query.getResultList();
//		return bus;
//	}

	public List<Routes> searchRoutesByBus(List<Integer> busId, String fromCity, String toCity) {
		List<Routes> routeDetails = new ArrayList<>();
		for (int i = 0; i < busId.size(); i++) {

			String sql = "select r from Routes r where (r.bus.busId=:busId) AND (r.fromCity=:fromCity) AND (r.toCity=:toCity)";
			TypedQuery<Routes> query = em.createQuery(sql, Routes.class);
			query.setParameter("busId", busId.get(i));
			query.setParameter("fromCity", fromCity);
			query.setParameter("toCity", toCity);
			
			Routes routes = query.getSingleResult();
			routeDetails.add(routes);
		}
		return routeDetails;
	}

	public List<Integer> totalSeatsBooked(List<Bus> bus, LocalDate dateOfJourney) {
			System.out.println(dateOfJourney);
		List<Integer> seatsAvailable = new ArrayList<>();
		for (int i = 0; i < bus.size(); i++) {

			Long tempseats = (Long) em.createQuery(
					"select count(s.seatId) from Seats s where (s.bus.busId= :busId) AND (s.dateOfJourney=:dateOfJourney)")
					.setParameter("busId", bus.get(i).getBusId()).setParameter("dateOfJourney", dateOfJourney)
					.getSingleResult();
			System.out.println(tempseats);
			seatsAvailable.add((int) (bus.get(i).getTotalSeat() - tempseats));
			System.out.println((int) (bus.get(i).getTotalSeat() - tempseats));
		}
		return seatsAvailable;
	}
	 public List<Bus> searchABus(String fromCity, String toCity, String day) {
	 String sql = "select bs from Bus bs where bs.busId in (select r.bus.busId from Routes r where r.fromCity=:"
	 + "from and r.toCity=:to and r.bus.busId in(select o.bus.busId from OperationalDays as o where o.operationalDays=:day))";
	 TypedQuery<Bus> query = em.createQuery(sql, Bus.class);
	 query.setParameter("from", fromCity);
	 query.setParameter("to", toCity);
	 query.setParameter("day", day);
	 List<Bus> bus=query.getResultList();
	 return bus;
	 }
	 
	 @Override
	 public List<Passenger> reservationDetail(LocalDate date) {
			String sql= "select p from Passenger p where p.ticket.ticketId in(select t.ticketId from ticket t where t.dateOfBooking =:date)";
			 TypedQuery<Passenger> query = em.createQuery(sql, Passenger.class);
			 query.setParameter("date", date);
			 List<Passenger> passenger= query.getResultList();
			return passenger;
		}
	 
	 
    @Override
	public List<Passenger> weeklyReservationDetail(LocalDate monday, LocalDate now) {
    	String sql= "select p from Passenger p where p.ticket.ticketId in(select t.ticketId from ticket t where t.dateOfBooking between :monday and :now)";
    	TypedQuery<Passenger> query = em.createQuery(sql, Passenger.class);
		 query.setParameter("monday", monday);
		 query.setParameter("now",now);
		 List<Passenger> passenger= query.getResultList();
		return passenger;
	}
    
	@Override
	public List<Passenger> monthlyReservationDetail(LocalDate start, LocalDate now) {
		
		String sql= "select p from Passenger p where p.ticket.ticketId in(select t.ticketId from ticket  t where t.dateOfBooking between :start and :now)";
    	TypedQuery<Passenger> query = em.createQuery(sql, Passenger.class);
		 query.setParameter("start", start);
		 query.setParameter("now",now);
		 List<Passenger> passenger= query.getResultList();
		return passenger;
	}
 
		@Override
		public List<Customer> noReservationCustomer() {
			
			String sql =" select c from Customer c where c.customerId not in (select t.customer.customerId from Ticket t)";
			TypedQuery<Customer> query = em.createQuery(sql,Customer.class);
			//query.setParameter("customerId", customerId);
			List<Customer> Customer=query.getResultList();
			System.out.println(Customer);
				return Customer;
		}	
		//select t from Transaction t where  t.transactionDate between :previousDate and  :currentDate
		@Override
		public List<Transaction> getLastMonthRecord(LocalDate previousDate, LocalDate currentDate ) {
			String sql = "select t from Transaction t where  t.transactionDate between :previousDate and  :currentDate";
//			TypedQuery<Transaction> qry = em.createQuery(sql, Transaction.class);		
			Query qry = em.createQuery(sql);
			qry.setParameter("currentDate",currentDate);
			qry.setParameter("previousDate", previousDate);
			
			List<Transaction> transactions=qry.getResultList();
			
			//System.out.println(transactions.size());
			List<Transaction> transaction= new ArrayList();
			for(int i= 0; i <transactions.size();i++) {
				Transaction t= new Transaction();
				Transaction t1 = (Transaction)transactions.get(i);
				System.out.println(transactions.get(i));
				t = em.find(Transaction.class, t1.getTransactionId());
				transaction.add(t);
			}
			System.out.println(transaction.toString());
			return transaction;
			
		}
		
		public double getPreviousProfits(LocalDate fromDate, LocalDate toDate) {
			
			double profit=0;
			String sql = "select sum(t.amount) from Transaction t where t.transactionDate between :fromDate and :toDate";
		    Query qry = em.createQuery(sql);
			qry.setParameter("fromDate", fromDate);
			qry.setParameter("toDate", toDate);		
			try {
				profit =  (double) qry.getSingleResult();
			}catch(NoResultException e) {		
							
			}					
			return profit ;
		}
		
		public String mostPrefferedTypesOfBuses() {
			
			String prefferedBusType = null;
			String sql = "select bs.BusType from Bus bs where bs.busId =(select max(t.bus.busId) from Ticket t)";
			//TypedQuery<Bus> qry = em.createQuery(sql, Bus.class);
			Query qry = em.createQuery(sql);
			try {
				prefferedBusType =  (String) qry.getSingleResult();
			}
			catch(NoResultException e){
				
			}
			return prefferedBusType;
		}
		
		public int getRegisteredCustomerId(String email) {
			String sql = "select cs from Customer cs where cs.email= :email";
			TypedQuery<Customer> qry = em.createQuery(sql, Customer.class);
			qry.setParameter("email", email);
			Customer c = qry.getSingleResult();
			System.out.println(c.getCustomerId());
			return c.getCustomerId();

		}
		
	public boolean checkAvailibilityofBus(String day, int busid) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean checkSeatsavailability(LocalDate date, int busid) {
		// TODO Auto-generated method stub
		return false;
	}

	public Bus findBus(int busid) {
		String sql = "select bs from Bus bs where bs.busId=:busid";
		TypedQuery<Bus> query = em.createQuery(sql, Bus.class);
		query.setParameter("busid", busid);
		Bus bus = query.getSingleResult();
		// Bus bus=em.find(Bus.class, busid);
		return bus;
	}

	public boolean addTicketAndPassengerWithRegisteredCustomers(Ticket ticket, List<Passenger> passenger, int cust_id) {
		// TODO Auto-generated method stub
		return false;
	}



	public int getCustomerId(String email) {
		return 0;
	}

	

	public int checkRegisteredUser(String email) {
		String sql = "select cs from Customer cs where cs.email= :email";
		TypedQuery<Customer> qry = em.createQuery(sql, Customer.class);
		qry.setParameter("email", email);
		Customer c = new Customer();
		try {
			c = qry.getSingleResult();
		} catch (NoResultException nre) {
			// Ignore this because as per your logic this is ok!
		}

		if(c.getEmail()==null){
			return 0;
		}
		else if(c.getPassword()==null) {
			return c.getCustomerId();
		}
		return -1;
	}

	@Transactional
	public int addTicketAndPassengerWithRegisteredCustomers(Ticket ticket, List<Passenger> passenger,
			List<Seats> seats, Transaction transaction) {
		ticket.setTransaction(transaction);

		ticket.setPassenger(passenger);
		ticket.setSeats(seats);
		transaction.setTicket(ticket);
		for (Passenger p : passenger) {

			p.setTicket(ticket);

		}
		for (Seats s : seats) {

			s.setTicket(ticket);
		}
		Ticket t=new Ticket();
		try {
				t=em.merge(ticket);
		}catch (NoResultException e) {
			// TODO: handle exception
		}
		return t.getTicketId();

	}

	@Override
	public Customer findByEmailPassword(String email, String password) {
		return (Customer) em.createQuery("select c from Customer c where c.email=:em and c.password=:pw")
				.setParameter("em", email).setParameter("pw", password).getSingleResult();
	}

	@Override
	public boolean isValidTicket(int ticketId, int customerId) {

		String sql = "select t from Ticket t where t.ticketId=:ticketId AND t.customer.customerId =:customerId";
		TypedQuery<Ticket> qry = em.createQuery(sql, Ticket.class);
		qry.setParameter("ticketId", ticketId);
		qry.setParameter("customerId", customerId);
		Ticket t = new Ticket();
		try {
			t = qry.getSingleResult();
		} catch (NoResultException nre) {
			// Ignore this because as per your logic this is ok!
		}
		System.out.println(t);
		if (t.getTicketId() == 0)
			return false;

		return true;
	}

	@Override
	public boolean isValidTicketDate(int ticketId) {
		LocalDate currentDate = LocalDate.now();
		System.out.println(currentDate);
		String sql = "select t from Ticket t where t.ticketId=:ticketId AND t.dateOfJourney >:currentDate";
		TypedQuery<Ticket> qry = em.createQuery(sql, Ticket.class);
		qry.setParameter("ticketId", ticketId);
		qry.setParameter("currentDate", currentDate);
		Ticket t = new Ticket();
		try {
			t = qry.getSingleResult();
		} catch (NoResultException nre) {
			// Ignore this because as per your logic this is ok!
		}
		System.out.println(t);
		if (t.getTicketId() == 0)
			return false;

		return true;
	}
	

	


	@Override
	public Customer isValidCustomerId(int customerId) {
		Customer customer=new Customer();
		
		try {
			 customer=em.find(Customer.class, customerId);
		} catch (NoResultException nre) {
			// Ignore this because as per your logic this is ok!
		}
		
		
		return customer;
	}

	@Override
	@Transactional
	public boolean checkOldPassword(int customerId, String oldPassword) {
		Customer customer=new Customer();
		try {
			 customer=em.find(Customer.class, customerId);
		} catch (NoResultException nre) {
			// Ignore this because as per your logic this is ok!
		}
		
		System.out.println(customer.getPassword());
		System.out.println(oldPassword);
		if(customer.getPassword().equals(oldPassword)) {
			System.out.println("inside if");
			return true;
		}
		return false;
	}


	@Override
	public List<Integer> fetchNoOfSeats(int busId, LocalDate dateOfJourney) {
		List<Integer> noOfSeats=new ArrayList<>();
		String sql="select s.seats from Seats s where s.dateOfJourney=:dateOfJourney AND s.bus.busId=:busId";
		Query qry = em.createQuery(sql);
		qry.setParameter("dateOfJourney", dateOfJourney);
		qry.setParameter("busId", busId);
	
		try {
			noOfSeats = qry.getResultList();
		} catch (NoResultException nre) {
			// Ignore this because as per your logic this is ok!
		}
		return noOfSeats;
	}

	public List<Ticket> fetchBookingsOfCustomer(int customerId){
		
		String sql= "select t from Ticket t where t.customer.customerId=:customerId";
    	TypedQuery<Ticket> query = em.createQuery(sql, Ticket.class);
		 query.setParameter("customerId",customerId);
		 
		List<Ticket> ticket= query.getResultList();
		return ticket;
	}


// public Bus findBus(int busid){
// String sql = "select bs from Bus bs where bs.busId=:busid";
// TypedQuery<Bus> query = em.createQuery(sql, Bus.class);
// query.setParameter("busid", busid);
// Bus bus=query.getSingleResult();
// Bus bus=em.find(Bus.class, busid);
// return bus.getChilds().size();
// }
}