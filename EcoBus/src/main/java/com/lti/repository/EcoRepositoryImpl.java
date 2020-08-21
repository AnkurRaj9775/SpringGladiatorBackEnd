package com.lti.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

	public boolean cancelTicket(int ticketId, String email) {
		// TODO Auto-generated method stub
		return false;
	}

	public Ticket searchTicket(int ticketId, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isValidEmail(String email) {
		String sql = "select cs from Customer cs where cs.email= :email";
		TypedQuery<Customer> qry = em.createQuery(sql, Customer.class);
		qry.setParameter("email", email);
		List<Customer> customer = qry.getResultList();
		if (customer.isEmpty())
			return false;

		return true;
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

	public List<Bus> searchABus(String fromCity, String toCity, String day) {
		String sql = "select bs from Bus bs where bs.busId in"
				+ "(select r.bus from Routes r Join OperationalDays o ON r.fromCity=:from "
				+ "and r.toCity=:to and o.operationalDays=:day)";
		TypedQuery<Bus> query = em.createQuery(sql, Bus.class);
		query.setParameter("from", fromCity);
		query.setParameter("to", toCity);
		query.setParameter("day", day);
		List<Bus> bus = query.getResultList();
		return bus;
	}
	// public List<Bus> searchABus(String fromCity, String toCity, String day) {
	// String sql = "select bs from Bus bs where bs.busId in"
	// + "(select r.bus from Routes r where r.fromCity=:"
	// + "from and r.toCity=:to and r.bus in(select o.bus from OperationalDays o
	// where o.operationalDays=:day))";
	// TypedQuery<Bus> query = em.createQuery(sql, Bus.class);
	// query.setParameter("from", fromCity);
	// query.setParameter("to", toCity);
	// query.setParameter("day", day);
	// List<Bus> bus=query.getResultList();
	// return bus;
	// }

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

	// public boolean addTicketAndPassengerWithUnregisteredCusomer(Ticket
	// ticket, Passenger passenger, Customer customer) {
	// // TODO Auto-generated method stub
	// return false;
	// }

	public int getCustomerId(String email) {
		return 0;
	}

	public int getRegisteredCustomerId(String email) {
		String sql = "select cs from Customer cs where cs.email= :email";
		TypedQuery<Customer> qry = em.createQuery(sql, Customer.class);
		qry.setParameter("email", email);
		Customer c = qry.getSingleResult();
		System.out.println(c.getCustomerId());
		return c.getCustomerId();
		
	}

	public boolean checkRegisteredUser(String email) {
		String sql = "select cs from Customer cs where cs.email= :email";
		TypedQuery<Customer> qry = em.createQuery(sql, Customer.class);
		qry.setParameter("email", email);
		Customer c=new Customer();
		try {
		c = qry.getSingleResult();
		} catch (NoResultException nre){
			//Ignore this because as per your logic this is ok!
		}
		
		if (c.getPassword() == null)
			return false;
		return true;

	}
	@Transactional
	public boolean addTicketAndPassengerWithRegisteredCustomers(Ticket ticket, List<Passenger> passenger,
			List<Seats> seats,Transaction transaction) {
			ticket.setTransaction(transaction);
			
			ticket.setPassenger(passenger);
			ticket.setSeats(seats);
			transaction.setTicket(ticket);
			for (Passenger p : passenger) {
				System.out.println("p counter");
				p.setTicket(ticket);
				}
			for( Seats s: seats){
				System.out.println("s counter");
				s.setTicket(ticket);
			}
			
			
			em.merge(ticket);
		
			return true;
	
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