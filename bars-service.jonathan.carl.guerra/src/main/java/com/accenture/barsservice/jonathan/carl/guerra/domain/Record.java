package com.accenture.barsservice.jonathan.carl.guerra.domain;

import java.time.LocalDate;

public class Record {

	private int billingCycle;
	private LocalDate startDate, endDate;
	private String accountName, firstName, lastName;
	private double amount;

	public Record() {
		// non-arg
	}

	// parameterized
	public Record(int billingCycle, LocalDate startDate, 
			LocalDate endDate, String accountName, String firstName,
			String lastName, double amount) {
		this.billingCycle = billingCycle;
		this.startDate = startDate;
		this.endDate = endDate;
		this.accountName = accountName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.amount = amount;
	}

	//setters and getters
	public int getBillingCycle() {
		return billingCycle;
	}

	public void setBillingCycle(int billingCycle) {
		this.billingCycle = billingCycle;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	
}
