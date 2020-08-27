package com.lti.bridge;

import java.util.ArrayList;
import java.util.List;

import com.lti.model.Ticket;

public class MyBookingDetails extends Status {

	private List<Ticket> mybookings= new ArrayList<>();

	public List<Ticket> getMybookings() {
		return mybookings;
	}

	public void setMybookings(List<Ticket> mybookings) {
		this.mybookings = mybookings;
	}
	
	
}
