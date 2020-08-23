package com.lti.dto;

public class UpdateWallet {

	
	private double walletAmount;
	private int customerId;
	

	public int getCustId() {
		return customerId;
	}

	public void setCustId(int custId) {
		this.customerId = custId;
	}

	public double getWalletAmount() {
		return walletAmount;
	}

	public void setWalletAmount(double walletAmount) {
		this.walletAmount = walletAmount;
	}
	
	
}
