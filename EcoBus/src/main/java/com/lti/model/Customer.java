package com.lti.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
public class Customer {

	@Id
	@SequenceGenerator(name="seq_cust",initialValue=10101,allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cust")
	private int customerId;
	
	@Column
	private String name;
	
	@Column(nullable=false,unique=true)
	private String email;
	
	@Column
	private String password;
	
	@Column
	private String contact;
	
	@Column
	private int age;
	
	@Column
	private String gender;
	
	@Column
	private double walletBalance;
	
	@OneToMany(mappedBy="customer",cascade=CascadeType.ALL)
	private List<Ticket> ticket;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(double walletBalance) {
		this.walletBalance = walletBalance;
	}

	public List<Ticket> getTicket() {
		return ticket;
	}

	public void setTicket(List<Ticket> ticket) {
		this.ticket = ticket;
	}

	
	
	
	
}
