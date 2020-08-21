package com.lti.model;

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

import org.hibernate.annotations.ManyToAny;

@Entity
public class OperationalDays {

	@Id
	@SequenceGenerator(name = "seq_operational", initialValue = 1010100, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_operational")
	private int operationalId;
	
	@Column
	private String operationalDays;
	
	@ManyToOne
	@JoinColumn(name="bus_id")
	private Bus bus;

	public int getOperationalId() {
		return operationalId;
	}

	public void setOperationalId(int operationalId) {
		this.operationalId = operationalId;
	}

	public String getOperationalDays() {
		return operationalDays;
	}

	public void setOperationalDays(String operationalDays) {
		this.operationalDays = operationalDays;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	@Override
	public String toString() {
		return "OperationalDays [operationalId=" + operationalId + ", operationalDays=" + operationalDays + "]";
	}
	
	
}
