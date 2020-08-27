package com.lti.bridge;

import java.time.LocalDate;

public class TransactionDetailsForRecord {
   private int transactionId;
   private double amount;
   private LocalDate transactionDate;
   private int ticketId;
   
   
public int getTransactionId() {
	return transactionId;
}
public void setTransactionId(int transactionId) {
	this.transactionId = transactionId;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
public LocalDate getTransactionDate() {
	return transactionDate;
}
public void setTransactionDate(LocalDate transactionDate) {
	this.transactionDate = transactionDate;
}
public int getTicketId() {
	return ticketId;
}
public void setTicketId(int ticketId) {
	this.ticketId = ticketId;
}
   
   
   
   
}
