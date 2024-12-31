package com.example.atm;

import java.util.ArrayList;
import java.util.Scanner;

public class ATMInterface {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		double balance = 10000.0;
		int pin = 123;
		int attempts = 0;
		boolean authenticated = false;
		
		ArrayList<String> miniStatement = new ArrayList<>();
		
		while(attempts < 3) {
			System.out.println("Enter ATM Pin: ");
			int enteredPin = sc.nextInt();
			
			if(enteredPin == pin) {
				authenticated = true;
				System.out.println("Account Authorized!");
				break;
			}
			else {
				attempts++;
				System.out.println("Incorrect PIN. Attempts remianing:" + (3- attempts));
			}
		}
		if (!authenticated) {
			System.out.println("Too many incorrect attempts. Exiting...");
			sc.close();
			return;
		}
		
		while(true) {
			System.out.println("\n ATM Menu:");
			System.out.println("1. View Available Balance");
			System.out.println("2. Withdraw Amount");
			System.out.println("3. Deposit Amount");
			System.out.println("4. View Mini Statement");
			System.out.println("5. Exit");
			System.out.println("Enter Choice: ");
			
			int choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				System.out.println("Your Current Blalance is: $" +balance);
				break;
				
			case 2:
				System.out.print("Enter amount to withdraw: ");
				double withdrawAmount = sc.nextDouble();
				
				if(withdrawAmount > 0 && withdrawAmount <= balance) {
					System.out.print("Confirm? (Y/N): ");
					char confirmWithdraw = sc.next().toUpperCase().charAt(0);
					
					if (confirmWithdraw == 'Y') {
						balance -= withdrawAmount;
						miniStatement.add("Withdrawn: $" + withdrawAmount);
						System.out.println("Collect the cash $" +withdrawAmount);
						System.out.println("Loading Accoutn Balance.....");
						System.out.println("Your Current Balance is: $" + balance);
					}
					else {
						System.out.println("Transcation Cancelled. ");
					}
				} 
				else if (withdrawAmount > balance) {
					System.out.println("Insufficient Balance.");
				}
				else {
					System.out.println("Invalid Amount. Please try again.");
				}
				break;
				
			case 3:
				System.out.print("Enter amount to deposit: ");
				double depositAmount = sc.nextDouble();
				
				if(depositAmount > 0) {
					balance += depositAmount;
					miniStatement.add("Deposited: $" + depositAmount);
					System.out.println("Deposit successful. Your Current Balance is: $" +balance);
				}
				else {
					System.out.println("Invalid Amount. Please try again.");
				}
				break;
				
			case 4:
				System.out.println("Mini Statement:");
				if(miniStatement.isEmpty()) {
					System.out.println("No transaction yet.");
				}
				else {
					for(String transaction : miniStatement) {
						System.out.println(transaction);
					}
				}
				System.out.println("Available Balance: $" +balance);
				break;
				
			case 5:
				System.out.println("Thank you for using the ATM. Goodbye!");
				sc.close();
				return;
				
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}
}
