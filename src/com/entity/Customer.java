package com.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Customer_Details")

@NamedQueries({
		@NamedQuery(name = "updateCustomerFirstName", query = "UPDATE Customer SET customerFirstName = :updatedFirstName WHERE customerId =:customerID"),
		@NamedQuery(name = "updateCustomerLastName", query = "UPDATE Customer SET customerLastName = :updatedLastName WHERE customerId =:customerID") })
public class Customer {

	@Id
	private int customerId;
	private String customerFirstName;
	private String customerLastName;
	private long customerMobileNumber;
	private String customerEmail;
	private String customerDOB;
	private String customerAadharNumber;
	private String customerPanNumber;
	private String customerAddress;
	private String customerCity;
	private String customerState;
	private int customerPincode;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "accountId")
	private Account account;

	public String getCustomerDOB() {
		return customerDOB;
	}

	public void setCustomerDOB(String customerDOB) {
		this.customerDOB = customerDOB;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public long getCustomerMobileNumber() {
		return customerMobileNumber;
	}

	public void setCustomerMobileNumber(long customerMobileNumber) {
		this.customerMobileNumber = customerMobileNumber;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerAadharNumber() {
		return customerAadharNumber;
	}

	public void setCustomerAadharNumber(String customerAadharNumber) {
		this.customerAadharNumber = customerAadharNumber;
	}

	public String getCustomerPanNumber() {
		return customerPanNumber;
	}

	public void setCustomerPanNumber(String customerPanNumber) {
		this.customerPanNumber = customerPanNumber;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerCity() {
		return customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	public String getCustomerState() {
		return customerState;
	}

	public void setCustomerState(String customerState) {
		this.customerState = customerState;
	}

	public int getCustomerPincode() {
		return customerPincode;
	}

	public void setCustomerPincode(int customerPincode) {
		this.customerPincode = customerPincode;
	}

	@Override
	public String toString() {
		return "Customer [customerId = " + customerId + ", customerFirstName = " + customerFirstName
				+ ", customerLastName = " + customerLastName + ", customerMobileNumber = " + customerMobileNumber
				+ ", customerEmail = " + customerEmail + ", customerDOB = " + customerDOB + ", customerAadharNumber = "
				+ customerAadharNumber + ", customerPanNumber = " + customerPanNumber + ", customerAddress = "
				+ customerAddress + ", customerCity = " + customerCity + ", customerState =" + customerState
				+ ", customerPincode = " + customerPincode + "]";
	}

}
