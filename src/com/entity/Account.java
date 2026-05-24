package com.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({ @NamedQuery(name = "getAccountBalance", query = "FROM Account WHERE accountNumber = :accountNumber") })
public class Account {

	@Id
	private int accountId;
	@Column(unique = true)
	private long accountNumber;
	private long accountcifNumber;
	//private String accountType;
	private double accountBalance;
	private String branchName;
	private String ifscCode;
	private LocalDate AccountDateOfIssue;
	
	
	@Enumerated(EnumType.STRING)
	private AcType AccountType;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fromAccount")
	private List<Transactions> sentTransactions;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "toAccount")
	private List<Transactions> recieverTransactions;
		

	@OneToOne(mappedBy = "account")
	private Customer customer;
	
	 
	

	

	public AcType getAccountType() {
		return AccountType;
	}

	public void setAccountType(AcType accountType) {
		AccountType = accountType;
	}

	public List<Transactions> getSentTransactions() {
		return sentTransactions;
	}

	public void setSentTransactions(List<Transactions> sentTransactions) {
		this.sentTransactions = sentTransactions;
	}

	public List<Transactions> getRecieverTransactions() {
		return recieverTransactions;
	}

	public void setRecieverTransactions(List<Transactions> recieverTransactions) {
		this.recieverTransactions = recieverTransactions;
	}


	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public long getAccountcifNumber() {
		return accountcifNumber;
	}

	public void setAccountcifNumber(long accountcifNumber) {
		this.accountcifNumber = accountcifNumber;
	}

//	public String getAccountType() {
//		return accountType;
//	}
//
//	public void setAccountType(String accountType) {
//		this.accountType = accountType;
//	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}


	public LocalDate getAccountDateOfIssue() {
		return AccountDateOfIssue;
	}

	public void setAccountDateOfIssue(LocalDate accountDateOfIssue) {
		AccountDateOfIssue = accountDateOfIssue;
	}

	@Override
	public String toString() {
		return "Account [accountId = " + accountId + ", accountNumber = " + accountNumber + ", accountcifNumber = "
				+ accountcifNumber + ", accountBalance = " + accountBalance + ", branchName = " + branchName + ", ifscCode = "
				+ ifscCode + ", AccountDateOfIssue = " + AccountDateOfIssue + ", AccountType = " + AccountType
				+ ", sentTransactions = " + sentTransactions + ", recieverTransactions = " + recieverTransactions + "]";
	}



}
