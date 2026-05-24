package com.serviceIMPL;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.Util.HibernateUtil;
import com.bank.generator.UniqueNumber;
import com.customException.AccountNotFoundException;
import com.customException.CustomerNotFoundException;
import com.customException.EnvalidEmailException;
import com.customException.IdAlreadyExists;
import com.customException.InsufficientBalanceException;
import com.customException.InvalidInputException;
import com.customException.InvalideMonbileNumberException;
import com.customException.InvalidePanNumberException;
import com.customException.SameAccountException;
import com.customException.TransactionNotFoundException;
import com.entity.AcType;
import com.entity.Account;
import com.entity.Customer;
import com.entity.TransactionStatus;
import com.entity.TransactionType;
import com.entity.Transactions;
import com.service.Bank;
import com.validation.ValidationChecker;

public class Hdfc implements Bank {
	SessionFactory sf = HibernateUtil.getSessionFactory();
	Scanner sc = new Scanner(System.in);

	@Override
	public void addCustomer() throws EnvalidEmailException, InvalideMonbileNumberException, InvalidePanNumberException,
			IdAlreadyExists, Exception {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		Customer customer = new Customer();

		int customerId = UniqueNumber.genrateCustomerId();
		customer.setCustomerId(customerId);

		System.out.println("Enter customer first name :");
		String customerFirstName = sc.next();
		customer.setCustomerFirstName(customerFirstName);

		System.out.println("Enter customer last name :");
		String customerLastName = sc.next();
		customer.setCustomerLastName(customerLastName);

		long customerMobileNumber = ValidationChecker.checkMobileNumber();
		customer.setCustomerMobileNumber(customerMobileNumber);

		String cusomerEmail = ValidationChecker.emailValidation();
		customer.setCustomerEmail(cusomerEmail);

		System.out.println("Enter customer DOB :");
		String customerDOB = sc.next();
		customer.setCustomerDOB(customerDOB);

		String customerAadharNumber = ValidationChecker.adharValidation();
		customer.setCustomerAadharNumber(customerAadharNumber);

//		System.out.println("Enter customer pan number :");
		String customerPanNumber = ValidationChecker.validPanNumber();
		customer.setCustomerPanNumber(customerPanNumber);

		System.out.println("Enter customer address :");
		String customerAddress = sc.next();
		customer.setCustomerAddress(customerAddress);

		System.out.println("Enter customer city :");
		String customerCity = sc.next();
		customer.setCustomerCity(customerCity);

		System.out.println("Enter customer state :");
		String customerState = sc.next();
		customer.setCustomerState(customerState);

		System.out.println("Enter customer pin code :");
		int customerPinCode = sc.nextInt();
		customer.setCustomerPincode(customerPinCode);

		System.out.println("\n----------------------- Enter Account Details -----------------------\n");

		Account account = new Account();

		int accountId = UniqueNumber.genrateAccountId();
		account.setAccountId(accountId);

		long accountNumber = UniqueNumber.genrateAcNumber();
		account.setAccountNumber(accountNumber);

		long accountCifNumber = UniqueNumber.genrateAccountCifNumber();
		account.setAccountcifNumber(accountCifNumber);

//		System.out.println("Enter account type :");
//		String accountType = sc.next();
		account.setAccountType(AcType.SAVING);

		System.out.println("Enter account balance :");
		long accountBalance = sc.nextLong();
		account.setAccountBalance(accountBalance);

		System.out.println("Enter branch name :");
		String branchName = sc.next();
		account.setBranchName(branchName);

		String ifscCode = "HDFCN00" + UniqueNumber.genrateAccountIfscCode();
		account.setIfscCode(ifscCode);

		LocalDate today = LocalDate.now();
		account.setAccountDateOfIssue(today);

		customer.setAccount(account);

		session.save(customer);

		tx.commit();

		System.out.println("\n------------------ data saved successfuly -----------------");

	}

	@Override
	public void getCustomerDetailById() throws CustomerNotFoundException {

		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		System.out.println("Enter customer Id :");
		int customerId = sc.nextInt();

		Customer customer = session.get(Customer.class, customerId);

		if (customer != null) {
			System.out.println(customer);
		} else {
			throw new CustomerNotFoundException("CUSTOMER NOT FOUND EXCEPTION !");
		}

		tx.commit();
	}

	@Override
	public void updateCustomerdetails() {
		boolean flag = true;
		while (flag) {
			System.out.println("1. Update customer first name");
			System.out.println("2. Update customer last name");
			System.out.println("3. Exit");
			System.out.println("\nEnter your choice :");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				try {
					updateFirstName();
					System.out.println("\n----- First name updated successfully -----\n");
				} catch (CustomerNotFoundException c) {
					System.err.println("\n" + c.getMessage());
				}

				break;
			case 2:
				try {
					updateLastName();
					System.out.println("\n----- Last name updated successfully -----\n");
				} catch (CustomerNotFoundException c) {
					System.err.println("\n" + c.getMessage());
				}

				break;
			case 3:
				flag = false;
				break;

			default:
				System.out.println("Invalide choice ! Please enter valide inpute...!");
				break;
			}
		}
	}

	@Override
	public void deleteCustomer() throws CustomerNotFoundException {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		System.out.println("Enter customer Id : ");
		int custId = sc.nextInt();

		Customer customer = session.get(Customer.class, custId);

		if (customer != null) {
			session.delete(customer);
		} else {
			throw new CustomerNotFoundException("CUSTOMER NOT FOUND EXCEPTION !");
		}

		tx.commit();
		System.out.println("\n------------------------------------------------------------------");
		System.out.println("Customer Deleted successfully...!");
		System.out.println("------------------------------------------------------------------");
	}

	@Override
	public void getAccountDetails() throws AccountNotFoundException {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		System.out.println("Enter Account Id :");
		int accountId = sc.nextInt();
		Account account = session.get(Account.class, accountId);

		if (account != null) {
			System.out.println(account);
		} else {
			throw new AccountNotFoundException("ACCOUNT NOT FOUND EXCEPTION !");
		}

		tx.commit();
	}

	@Override
	public void checkAccountBalance() throws AccountNotFoundException {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		System.out.println("Enter account number :");
		long acNumber = sc.nextLong();

		Query query = session.createNamedQuery("getAccountBalance");
		query.setParameter("accountNumber", acNumber);
		List<Account> accountList = query.getResultList();

		if (accountList.isEmpty()) {
			throw new AccountNotFoundException("ACCOUNT NOT FOUND EXCEPTION !");
		}

		for (Account account : accountList) {
			System.out.println("\nAccount balance is : " + account.getAccountBalance());
		}

		tx.commit();
	}

	@Override
	public void depositeAmount() throws AccountNotFoundException, InvalidInputException {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		Transactions transactions = new Transactions();

		System.out.println("Enter account number :");
		long accountNumber = sc.nextLong();

		System.out.println("Enter amount you want to deposit :");
		double depositAmount = sc.nextDouble();

		if (depositAmount <= 0) {
			throw new InvalidInputException("Invalid Deposit Amount !");
		}

		Account account = session.createQuery("FROM Account WHERE accountNumber = :ac", Account.class)
				.setParameter("ac", accountNumber).uniqueResult();

		if (account == null) {

			throw new AccountNotFoundException("ACCOUNT NOT FOUND WITH ACCOUNT NUMBER : " + accountNumber);
		}

		account.setAccountBalance(account.getAccountBalance() + depositAmount);

		transactions.setFromAccount(account);
		transactions.setTransasctionUtrNumber(UniqueNumber.transactionUtrNumber());
		transactions.setTransactionStatus(TransactionStatus.SUCCESS);
		transactions.setTransactionType(TransactionType.DEPOSIT);
		transactions.setTransactionAmount(depositAmount);
		transactions.setTransactionId("T" + UniqueNumber.transactionId());
		transactions.setTransactionDate(LocalDateTime.now());

		session.save(transactions);

//		query.executeUpdate();
		tx.commit();
		System.out.println("\n------------------------------------------------------------------");
		System.out.println("Amount deposited successfully...!");
		System.out.println("------------------------------------------------------------------");
	}

	@Override
	public void withdrowAmount()
			throws InsufficientBalanceException, InvalidInputException, AccountNotFoundException, Exception {

		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		Transactions transactions = new Transactions();

		System.out.println("Enter account number : ");
		long accountNumber = sc.nextLong();

		System.out.println("Enter withdraw amount : ");
		double withdrawAmount = sc.nextDouble();

		Query<Account> query = session.createQuery("FROM Account WHERE accountNumber = :accNo");

		query.setParameter("accNo", accountNumber);

		Account account = query.uniqueResult();

		if (account != null) {

			double currentBalance = account.getAccountBalance();

			if (currentBalance < withdrawAmount) {
				throw new InsufficientBalanceException("Inssufficient Balance! Exception");
			}
			if (withdrawAmount > 0) {
				double updatedBalance = currentBalance - withdrawAmount;
				account.setAccountBalance(updatedBalance);

				transactions.setFromAccount(account);
				transactions.setTransasctionUtrNumber(UniqueNumber.transactionUtrNumber());
				transactions.setTransactionStatus(TransactionStatus.SUCCESS);
				transactions.setTransactionType(TransactionType.WITHDRAW);
				transactions.setTransactionAmount(withdrawAmount);
				transactions.setTransactionId("T" + UniqueNumber.transactionId());
				transactions.setTransactionDate(LocalDateTime.now());

				System.out.println("\n------------------------------------------------------------------");
				System.out.println("Withdraw Successful.");
				System.out.println("Remaining Balance : " + updatedBalance);
				System.out.println("------------------------------------------------------------------");
			} else {
				throw new InvalidInputException("Invalide input...!");
			}

		} else {
			throw new AccountNotFoundException("ACCOUNT NOT FOUND EXCEPTION !");
		}
		session.save(transactions);
		session.update(account);
		tx.commit();
	}

	@Override
	public void transferMoney() throws Exception {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		Account fromAccount = new Account();
		Account toAccount = new Account();

		Transactions transactions = new Transactions();

		System.out.println("Enter sender account number :");
		long senderAccountNumber = sc.nextLong();

		Query query1 = session.createQuery("from Account where accountNumber = :senderAcNo", Account.class);
		query1.setParameter("senderAcNo", senderAccountNumber);

		fromAccount = ((org.hibernate.Query<Account>) query1).uniqueResult();

		if (fromAccount != null) {

			System.out.println("Enter account Number to transfer : ");
			long receiverAccountNumber = sc.nextLong();

			if (senderAccountNumber == receiverAccountNumber) {
				throw new SameAccountException("Both Account Number Is Same...!");
			}

			Query query2 = session.createQuery("from Account where accountNumber = :receiverAcNo", Account.class);
			query2.setParameter("receiverAcNo", receiverAccountNumber);

			toAccount = ((org.hibernate.Query<Account>) query2).uniqueResult();

			if (toAccount != null) {

				System.out.println("Enter amount to transfer : ");
				double transferAmount = sc.nextDouble();

				if (fromAccount.getAccountBalance() > transferAmount) {
					fromAccount.setAccountBalance(fromAccount.getAccountBalance() - transferAmount);
					toAccount.setAccountBalance(toAccount.getAccountBalance() + transferAmount);

					transactions.setFromAccount(fromAccount);
					transactions.setToAccount(toAccount);

					transactions.setTransasctionUtrNumber(UniqueNumber.transactionUtrNumber());
					transactions.setTransactionStatus(TransactionStatus.SUCCESS);
					transactions.setTransactionType(TransactionType.TRANSFER);
					transactions.setTransactionAmount(transferAmount);
					transactions.setTransactionId("T" + UniqueNumber.transactionId());
					transactions.setTransactionDate(LocalDateTime.now());

					session.save(transactions);
					System.out.println("\n------------------------------------------------------------------");
					System.out.println("Money Transfer successfully...!");
					System.out.println("------------------------------------------------------------------\n");

				} else {
					throw new InsufficientBalanceException("INSUFFICIENT BALANCE EXCEPTION !");

				}
			} else {
				throw new AccountNotFoundException("ACCOUNT NOT FOUND EXCEPTION !");
			}

		} else {
			throw new AccountNotFoundException("ACCOUNT NOT FOUND EXCEPTION !");
		}

		tx.commit();

	}

	@Override
	public void getTransactionsByAccount() throws TransactionNotFoundException, AccountNotFoundException {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		System.out.println("Enter account number : ");
		long accountNumber = sc.nextLong();

		Query<Account> query1 = session.createQuery("FROM Account WHERE accountNumber = :accNo", Account.class);

		query1.setParameter("accNo", accountNumber);

		Account account = query1.uniqueResult();

		if (account == null) {

			throw new AccountNotFoundException("ACCOUNT NOT FOUND EXCEPTION !");

		}

		Query<Transactions> query2 = session.createQuery(
				"FROM Transactions " + "WHERE fromAccount.accountId = :id " + "OR toAccount.accountId = :id",
				Transactions.class);

		query2.setParameter("id", account.getAccountId());

		List<Transactions> list = query2.getResultList();

		if (list.isEmpty()) {

			throw new TransactionNotFoundException("TRANSACTION NOT FOUND EXCEPTION...!");
		} else {

			for (Transactions t : list) {

				System.out.println(t);
			}
		}

		tx.commit();
		session.close();
	}

	@Override
	public void getTransactionsByAmount() throws TransactionNotFoundException {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		System.out.println("Enter Transaction Amount : ");
		double transactionsAmount = sc.nextDouble();

		Query query = session.createQuery("FROM Transactions WHERE transactionAmount = :transactionAmount");
		query.setParameter("transactionAmount", transactionsAmount);

		List<Transactions> transactionsList = query.getResultList();

		if (transactionsList.isEmpty()) {
			throw new TransactionNotFoundException("TRANSACTION NOT FOUND EXCEPTION...!");
		}

		System.out.println("\n------------------------------------------------------------------");
		for (Transactions transactions : transactionsList) {

			System.out.println(transactions);
		}
		System.out.println("------------------------------------------------------------------");

		tx.commit();

	}

	// -----------------------------------------------------------//----------------------------------------------------
	// this method is use to update customer first name
	private void updateFirstName() throws CustomerNotFoundException {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		System.out.println("Enter customer ID : ");
		int customerId = sc.nextInt();

		System.out.println("Enter customer new name : ");
		String newName = sc.next();

		Customer customer = session.get(Customer.class, customerId);

		if (customer == null) {
			throw new CustomerNotFoundException("CUSTOMER NOT FOUND EXCEPTION !");
		}

		Query query = session.createNamedQuery("updateCustomerFirstName");
		query.setParameter("updatedFirstName", newName);
		query.setParameter("customerID", customerId);

		query.executeUpdate();

		tx.commit();
	}

	// this method is use to update customer last name
	private void updateLastName() throws CustomerNotFoundException {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		System.out.println("Enter customer Id :");
		int customerId = sc.nextInt();

		System.out.println("Enter new last name :");
		String newLastName = sc.next();

		Customer customer = session.get(Customer.class, customerId);

		if (customer == null) {
			throw new CustomerNotFoundException("CUSTOMER NOT FOUND EXCEPTION !");
		}

		Query query = session.createNamedQuery("updateCustomerLastName");
		query.setParameter("customerID", customerId);
		query.setParameter("updatedLastName", newLastName);

		query.executeUpdate();

		tx.commit();
	}

}
