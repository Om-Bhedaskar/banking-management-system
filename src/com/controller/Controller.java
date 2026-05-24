package com.controller;

import java.util.Scanner;

import com.customException.AccountNotFoundException;
import com.customException.CustomerNotFoundException;
import com.customException.InsufficientBalanceException;
import com.customException.InvalidInputException;
import com.customException.TransactionNotFoundException;
import com.serviceIMPL.Hdfc;

public class Controller {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Hdfc hdfc = new Hdfc();

		boolean flag = true;

		System.out.println("---------------------------------------");
		System.out.println("WELCOME TO BANKING APPLICATION");
		System.out.println("---------------------------------------\n");
		while (flag) {

			showMenu();
			flag = performOperation(sc, hdfc, flag);

		}

		System.out.println("\n---------------------------------------");
		System.out.println("          THANKS FOR VISITING");
		System.out.println("---------------------------------------\n");

	}

	private static void showMenu() {
		System.out.println("\n 1. Add Customer");
		System.out.println(" 2. Customer Detail");
		System.out.println(" 3. Account Detail");
		System.out.println(" 4. Check Account Balance");
		System.out.println(" 5. Update Customer Detail");
		System.out.println(" 6. Delete Customer Detail");
		System.out.println(" 7. Deposite Amount");
		System.out.println(" 8. Withdrow Money");
		System.out.println(" 9. Transfer Money");
		System.out.println("10. Account Transaction Detail");
		System.out.println("11. Amount Transaction Detail");
		System.out.println("12. Exit");
	}

	private static boolean performOperation(Scanner sc, Hdfc hdfc, boolean flag) {
		System.out.println("\nEnter Your Choice :");
		int choice = sc.nextInt();

		switch (choice) {
		case 1:

			try {
				hdfc.addCustomer();
			} catch (Exception e) {
				System.err.println("\n" + e.getMessage());
			}

			break;
		case 2:
			try {
				hdfc.getCustomerDetailById();
			} catch (CustomerNotFoundException c) {
				System.err.println("\n" + c.getMessage());
			}

			break;
		case 3:
			try {
				hdfc.getAccountDetails();
			} catch (AccountNotFoundException a) {
				System.err.println("\n" + a.getMessage());
			}

			break;
		case 4:
			try {
				hdfc.checkAccountBalance();
			} catch (AccountNotFoundException a) {
				System.err.println("\n" + a.getMessage());
			}
			break;
		case 5:
			hdfc.updateCustomerdetails();
			break;
		case 6:
			try {
				hdfc.deleteCustomer();
			} catch (CustomerNotFoundException c) {
				System.err.println("\n" + c.getMessage());
			}

			break;
		case 7:
			try {
				hdfc.depositeAmount();
			} catch (Exception e) {
				System.err.println("\n" + e.getMessage());
			}

			break;
		case 8:
			try {
				hdfc.withdrowAmount();
			} catch (Exception e) {
				System.err.println("\n" + e.getMessage());
			}
			break;
		case 9:
			try {
				hdfc.transferMoney();
			} catch (Exception e) {
				System.err.println("\n" + e.getMessage());
			}
			break;
		case 10:
			try {
				hdfc.getTransactionsByAccount();
			} catch (Exception e) {
				System.err.println("\n" + e.getMessage());
			}
			
			break;
		case 11:
			try {
				hdfc.getTransactionsByAmount();
			} catch (TransactionNotFoundException t) {
				System.err.println("\n" + t.getMessage());
			}

			break;
		case 12:
			flag = false;
			break;
		default:
			System.out.println("\nInvalide Inpute...!");
			System.out.println("--------------------------------------------------------------------------");
			break;
		}
		return flag;
	}

}
