package com.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transactions {

	@Id
	private long transactionUtrNumber;
	private String transactionId;

	private double transactionAmount;
	private LocalDateTime transactionDate;

	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;

	@Enumerated(EnumType.STRING)
	private TransactionStatus transactionStatus;

	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	@ManyToOne
	@JoinColumn(name = "fromAccount_Id")
	private Account fromAccount;

	@ManyToOne
	@JoinColumn(name = "toAccount_Id")
	private Account toAccount;

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public long getTransasctionUtrNumber() {
		return transactionUtrNumber;
	}

	public void setTransasctionUtrNumber(long transactionUtrNumber) {
		this.transactionUtrNumber = transactionUtrNumber;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Account getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}

	public Account getToAccount() {
		return toAccount;
	}

	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}

	@Override
	public String toString() {
		return "\nTransactions [transactionUtrNumber = " + transactionUtrNumber + ", transactionId = " + transactionId
				+ ", transactionAmount = " + transactionAmount + ", transactionDate = " + transactionDate
				+ ", transactionType = " + transactionType + ", transactionStatus = " + transactionStatus
				+ ", fromAccount = " + fromAccount + ", toAccount = " + toAccount + "]";
	}

}
