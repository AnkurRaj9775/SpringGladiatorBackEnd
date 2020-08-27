package com.lti.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Ticket {

	
	@Id
	@SequenceGenerator(name = "seq_ticketid", initialValue = 1010100, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ticketid")
	private int ticketId;
	
	@Column
	private int noOfSeatsBooked;
	
	@Column
	private LocalDate dateOfJourney;
	
	@Column
	private LocalDate dateOfBooking;
	
	@Column
	private double totalCost;
	
	@Column
	private String fromCity;
	
	@Column
	private String toCity;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bus_id")
	@JsonIgnore
	private Bus bus;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	@JsonIgnore
	private Customer customer;
	
	@OneToMany(mappedBy="ticket",cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Seats> seats;

	@OneToMany(mappedBy="ticket",cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Passenger> passenger;
	
	@OneToOne(mappedBy="ticket",cascade=CascadeType.ALL)
	@JsonIgnore
	private Transaction transaction;

	
	
	
	
	
	
	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public List<Seats> getSeats() {
		return seats;
	}

	public void setSeats(List<Seats> seats) {
		this.seats = seats;
	}



	

	public List<Passenger> getPassenger() {
		return passenger;
	}

	public void setPassenger(List<Passenger> passenger) {
		this.passenger = passenger;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public int getNoOfSeatsBooked() {
		return noOfSeatsBooked;
	}

	public void setNoOfSeatsBooked(int noOfSeatsBooked) {
		this.noOfSeatsBooked = noOfSeatsBooked;
	}

	public LocalDate getDateOfJourney() {
		return dateOfJourney;
	}

	public void setDateOfJourney(LocalDate dateOfJourney) {
		this.dateOfJourney = dateOfJourney;
	}

	public LocalDate getDateOfBooking() {
		return dateOfBooking;
	}

	public void setDateOfBooking(LocalDate dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}


	
	
}
