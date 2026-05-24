package com.service;

import com.customException.AccountNotFoundException;
import com.customException.CustomerNotFoundException;
import com.customException.EnvalidEmailException;
import com.customException.InsufficientBalanceException;
import com.customException.InvalidInputException;
import com.customException.TransactionNotFoundException;

public interface Bank {

	public void addCustomer() throws Exception;

	public void getCustomerDetailById() throws CustomerNotFoundException;

	public void updateCustomerdetails();

	public void deleteCustomer() throws CustomerNotFoundException;

	public void getAccountDetails() throws AccountNotFoundException;

	public void checkAccountBalance() throws AccountNotFoundException;

	public void depositeAmount() throws AccountNotFoundException, InvalidInputException;

	public void withdrowAmount() throws Exception;

	public void transferMoney() throws Exception;

	public void getTransactionsByAccount() throws TransactionNotFoundException, AccountNotFoundException;

	public void getTransactionsByAmount() throws TransactionNotFoundException;

}
