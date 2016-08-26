package com.animalsinzoo;

import java.util.Scanner;

public class zoo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		String userMenuInput = null;
		boolean menuCorrect = false;
		
		System.out.println("Welcome to our Zoo!");
		
		
		menu(sc, menuCorrect);
		
		
	} //main

	private static void menu(Scanner sc, boolean menuCorrect) {
		String userMenuInput;
		do {
			System.out.println("Press 1 to view the list of animals.");
			System.out.println("Press 2 to add an animal to the list");
			System.out.println("Press 3 to delete an animal from the list");
			System.out.println("press 4 to exit");
			userMenuInput = sc.nextLine();
			switch (userMenuInput) {
			case "1":
				DAO.readFromDB();
				break;
			case "2":
				DAO.writeToDB();
				break;
			case "3":
				DAO.deleteFromDB();
			case "4":
				System.exit(0);
	
				

			default:
				System.out.println("You have made an invalid selection");
				menuCorrect = true;
				break;
			}
		} while (menuCorrect);
	}

} //class
