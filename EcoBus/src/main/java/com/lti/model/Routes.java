package com.lti.model;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
@Entity
public class Routes {

	@Id
	@SequenceGenerator(name = "seq_routeid", initialValue = 40001, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_routeid")
	private int routeId;
	
	@Column
	private String fromCity;
	
	@Column
	private String toCity;
	
	@Column
	private String departureTime;
	
	@Column
	private String arrivalTime;
	
	@Column
	private double Fare;
	
	@Column
	private String duration;
	
	@ManyToOne
	@JoinColumn(name="bus_id")
	private Bus bus;

	
	
	
	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
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

	public double getFare() {
		return Fare;
	}

	public void setFare(double fare) {
		Fare = fare;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Routes [routeId=" + routeId + ", fromCity=" + fromCity + ", toCity=" + toCity + ", departureTime="
				+ departureTime + ", arrivalTime=" + arrivalTime + ", Fare=" + Fare + ", duration=" + duration + "]";
	}

	

	
	
}
