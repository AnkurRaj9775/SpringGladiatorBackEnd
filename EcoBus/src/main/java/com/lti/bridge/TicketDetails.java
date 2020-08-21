package com.lti.bridge;

import java.time.LocalDate;

public class TicketDetails {
	private LocalDate dateOfBooking;
	private LocalDate dateOfJourney;
	private int noOfSeatsBooked;
	private double totalCost;
	private int busId;
	
	
	
	
	public LocalDate getDateOfBooking() {
		return dateOfBooking;
	}
	public void setDateOfBooking(LocalDate dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}
	public LocalDate getDateOfJourney() {
		return dateOfJourney;
	}
	public void setDateOfJourney(LocalDate dateOfJourney) {
		this.dateOfJourney = dateOfJourney;
	}
	public int getNoOfSeatsBooked() {
		return noOfSeatsBooked;
	}
	public void setNoOfSeatsBooked(int noOfSeatsBooked) {
		this.noOfSeatsBooked = noOfSeatsBooked;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public int getBusId() {
		return busId;
	}
	public void setBusId(int busId) {
		this.busId = busId;
	}
	
	

}
