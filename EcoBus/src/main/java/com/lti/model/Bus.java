package com.lti.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Bus {

	@Id
	@SequenceGenerator(name = "seq_busid", initialValue = 131100, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_busid")
	private int busId;
	
	@Column
	private int totalSeat;
	
	@Column
	private String busName;
	
	@Column
	private String BusType;
	
	@Column
	private String BusPlateNumber;
	
	@Column
	private String BusStatus;
	
	
	public String getBusPlateNumber() {
		return BusPlateNumber;
	}

	public void setBusPlateNumber(String busPlateNumber) {
		BusPlateNumber = busPlateNumber;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="driver_id")
	private Driver driver;

	
	@OneToMany(mappedBy="bus",cascade=CascadeType.ALL)
	private List<Routes> routes;
	
	@OneToMany(mappedBy="bus",cascade=CascadeType.ALL)
    private List<OperationalDays> operationalDays;
	
	@OneToMany(mappedBy="bus",cascade=CascadeType.ALL)
	private List<Ticket> ticket;
	
	@OneToMany(mappedBy="bus",cascade=CascadeType.ALL)//orphan removal???
	private List<Seats> seats;


	public List<Routes> getRoutes() {
		return routes;
	}

	public void setRoutes(List<Routes> routes) {
		this.routes = routes;
	}

	public List<OperationalDays> getOperationalDays() {
		return operationalDays;
	}

	public void setOperationalDays(List<OperationalDays> operationalDays) {
		this.operationalDays = operationalDays;
	}

	public int getBusId() {
		return busId;
	}

	public void setBusId(int busId) {
		this.busId = busId;
	}

	public int getTotalSeat() {
		return totalSeat;
	}

	public void setTotalSeat(int totalSeat) {
		this.totalSeat = totalSeat;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getBusType() {
		return BusType;
	}

	public void setBusType(String busType) {
		BusType = busType;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	
	public String getBusStatus() {
		return BusStatus;
	}

	public void setBusStatus(String busStatus) {
		BusStatus = busStatus;
	}

	public List<Ticket> getTicket() {
		return ticket;
	}

	public void setTicket(List<Ticket> ticket) {
		this.ticket = ticket;
	}

	public List<Seats> getSeats() {
		return seats;
	}

	public void setSeats(List<Seats> seats) {
		this.seats = seats;
	}

	@Override
	public String toString() {
		return "Bus [busId=" + busId + ", totalSeat=" + totalSeat + ", busName=" + busName + ", BusType=" + BusType
				+ ", BusPlateNumber=" + BusPlateNumber + ", driver=" + driver.getDriverId() + "]";
	}
	
	


//	
//

	
	
	

	

	
}
