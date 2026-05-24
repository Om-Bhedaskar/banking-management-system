package com.bank.generator;

public class UniqueNumber {

	// Generates and returns a random 11-digit account number.
	public static long genrateAcNumber() {
		long acNumber = (long) (Math.random() * Math.pow(10, 11));
		return acNumber;
	}

	// Generates and returns a random 11-digit account cif number.
	public static long genrateAccountCifNumber() {
		long cifNumber = (long) (Math.random() * Math.pow(10, 11));
		return cifNumber;
	}

	// Generates and returns a random 14-digit for the transaction id number.
	public static long transactionId() {
		long txrNumber = (long) (Math.random() * Math.pow(10, 14));
		return txrNumber;
	}

	// Generates and returns a random 12-digit for the transaction utr number.
	public static long transactionUtrNumber() {
		long utrNumber = (long) (Math.random() * Math.pow(10, 12));
		return utrNumber;
	}

	// Generates and returns a random 5-digit account ifsc code.
	public static long genrateAccountIfscCode() {
		long utrNumber = (long) (Math.random() * Math.pow(10, 5));
		return utrNumber;
	} 

	// Generates and returns a random 5-digit customer Id.
	public static int genrateCustomerId() {
		int customerId = (int) (Math.random() * Math.pow(10, 7));
		return customerId;
	}
	
	// Generates and returns a random 9-digit Account Id.
		public static int genrateAccountId() {
			int accountId = (int) (Math.random() * Math.pow(10, 9));
			return accountId;
		}

}
