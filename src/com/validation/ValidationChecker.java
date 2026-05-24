package com.validation;

import java.util.Scanner;
import java.util.regex.Pattern;

import com.customException.EnvalidEmailException;
import com.customException.InvaidAdharNumberException;
import com.customException.InvalideMonbileNumberException;
import com.customException.InvalideNameException;
import com.customException.InvalidePanNumberException;

public class ValidationChecker {

	Scanner sc = new Scanner(System.in);

	// Validates Aadhaar number format (12 digits, starts with 2–9).
	public static String adharValidation() throws InvaidAdharNumberException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter customer Aadhar number :");
		String adharCardNumber = sc.nextLine();
		if (Pattern.matches("[2-9]{1}[0-9]{3}[ ]{1}[0-9]{4}[ ]{1}[0-9]{4}", adharCardNumber)) {
			return adharCardNumber;
		} else {
			throw new InvaidAdharNumberException("Invalide Aadhar number exception ! please enter correct aadhar number -> xxxx xxxx xxxx");
		}
	}

	// Method to validate PAN Card Number
	public static String validPanNumber() throws InvalidePanNumberException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter customer pan number :");
		String panCardNumber = sc.next();
		if (Pattern.matches("[A-Z]{5}[0-9]{4}[A-Z]{1}", panCardNumber)) {
			return panCardNumber;
		} else {
			throw new InvalidePanNumberException("Invalide Pan Number Exception !");
		}

	}

	// Method for check Mobile Number Is valid or Not
	public static long checkMobileNumber() throws InvalideMonbileNumberException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter customer mobile number : ");
		long mobileStr = sc.nextLong();
		String moNumber = String.valueOf(mobileStr);
		if (Pattern.matches("[6-9]{1}[0-9]{9}", moNumber)) {
			return mobileStr;
		} else {
			throw new InvalideMonbileNumberException(
					"Invalid Mobile Number! It must start with 6-9 and contain 10 digits.");
		}

	}

	// Validates email format using regex.
	public static String emailValidation() throws EnvalidEmailException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter customer email : ");
		String email = sc.nextLine();

		if (Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", email)) {
			return email;
		} else {
			throw new EnvalidEmailException("INVALIDE EMAIL EXCEPTION !");
		}
	}

}
