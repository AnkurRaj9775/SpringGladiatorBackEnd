package com.lti.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Driver {

	@Override
	public String toString() {
		return "Driver [driverId=" + driverId + ", DriverName=" + DriverName + ", contact=" + contact + "]";
	}

	@Id
	@SequenceGenerator(name="seq_driverid", initialValue = 2200, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_driverid")
	private int driverId;
	
	@Column
	private String DriverName;
	
	@Column
	private String contact;
	
	@OneToOne(mappedBy="driver",cascade=CascadeType.MERGE)//check if delete driver is happenin or not without deleting the bus!
	private Bus bus;

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public String getDriverName() {
		return DriverName;
	}

	public void setDriverName(String driverName) {
		DriverName = driverName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	
	
	
	
}
