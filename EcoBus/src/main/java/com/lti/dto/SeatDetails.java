package com.lti.dto;


public class SeatDetails {
	private int seatNo;

	
	public SeatDetails() {
		
	}
	
	
	public SeatDetails(int seatNo) {
		super();
		this.seatNo = seatNo;
	}


	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}

	@Override
	public String toString() {
		return "SeatDetails [seatNo=" + seatNo + "]";
	}
	
	

}
