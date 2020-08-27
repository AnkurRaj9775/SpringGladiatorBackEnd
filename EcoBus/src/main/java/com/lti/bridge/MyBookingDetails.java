package com.lti.bridge;

import java.util.ArrayList;
import java.util.List;

import com.lti.model.Ticket;

public class MyBookingDetails extends Status {

	private List<MyBookTickets> mybookings= new ArrayList<>();

	public List<MyBookTickets> getMybookings() {
		return mybookings;
	}

	public void setMybookings(List<MyBookTickets> mybookings) {
		this.mybookings = mybookings;
	}
	
	

	
	
}
