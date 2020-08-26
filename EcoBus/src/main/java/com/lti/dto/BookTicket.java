package com.lti.dto;

import java.util.List;

public class BookTicket {

	
	private CustomerDetails customerDetails;
	
	private TicketDetails ticketDetails;
	
	private List<PassengerDetails> passengerDetails;
	
	private List<SeatDetails> seatDetails;

	public CustomerDetails getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(CustomerDetails customerDetails) {
		this.customerDetails = customerDetails;
	}

	public TicketDetails getTicketDetails() {
		return ticketDetails;
	}

	public void setTicketDetails(TicketDetails ticketDetails) {
		this.ticketDetails = ticketDetails;
	}

	public List<PassengerDetails> getPassengerDetails() {
		return passengerDetails;
	}

	public void setPassengerDetails(List<PassengerDetails> passengerDetails) {
		this.passengerDetails = passengerDetails;
	}

	public List<SeatDetails> getSeatDetails() {
		return seatDetails;
	}

	public void setSeatDetails(List<SeatDetails> seatDetails) {
		this.seatDetails = seatDetails;
	}
	
	
}
