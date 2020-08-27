package com.lti.bridge;

import java.time.LocalDate;

import javax.persistence.Column;

public class MyBookTickets {

	
private int ticketId;
	
	
	private int noOfSeatsBooked;
	

	private LocalDate dateOfJourney;
	
	
	private LocalDate dateOfBooking;
	
	
	private double totalCost;
	
	
	private String fromCity;
	

	private String toCity;
	
	
	private boolean cancelButton;


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


	public boolean isCancelButton() {
		return cancelButton;
	}


	public void setCancelButton(boolean cancelButton) {
		this.cancelButton = cancelButton;
	}
	
	
	
}
