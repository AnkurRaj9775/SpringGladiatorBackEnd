package com.lti.bridge;


public class SeatDetails {
	private int seatNo;

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

	public SeatDetails(int seatNo) {
		super();
		this.seatNo = seatNo;
	}

	public SeatDetails() {
		// TODO Auto-generated constructor stub
	}
	
	

}
