package com.lti.bridge;

/**
 * @author admin
 *
 */
public class BusDetails extends Status {

	private int busId;
	
	private String busName;
	
	private String busType;
	
	private String departureTime;
	
	private String arrivalTime;
	
	private String duration;
	
	private double fare;
	
	private int totalSeatsAvailable;

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public int getTotalSeatsAvailable() {
		return totalSeatsAvailable;
	}

	public void setTotalSeatsAvailable(int totalSeatsAvailable) {
		this.totalSeatsAvailable = totalSeatsAvailable;
	}

	public int getBusId() {
		return busId;
	}

	public void setBusId(int busId) {
		this.busId = busId;
	}

	@Override
	public String toString() {
		return "BusDetails [busId=" + busId + ", busName=" + busName + ", busType=" + busType + ", departureTime="
				+ departureTime + ", arrivalTime=" + arrivalTime + ", duration=" + duration + ", fare=" + fare
				+ ", totalSeatsAvailable=" + totalSeatsAvailable + "]";
	}

	

	
	
}
