// Varun Shourie, MP2, CIS340, Tuesday/Thursday, 3:00PM-4:15PM

import java.util.Scanner;

public class Utilities {
	
	// Made static so only one copy remains throughout the entire program.
	private static Scanner scanner = new Scanner(System.in);
	
	// Prints a neatly preformatted header for the program. 
	public static void displayHeader(String displayString) {
		System.out.printf("\t\t%s\n\n", displayString);
	}
	
	// No setter is included for this getter since we do not need to alter the scanner in any way.
	public static Scanner getScanner() {
		return scanner;
	}
	
	public static void insertFiveBlankLines() {
		System.out.print("\n\n\n\n\n\n");
	}
	
	// Inserts a prompt for a pause in the program.
	public static void pause(String displayString) {
		System.out.print(displayString);
		scanner.nextLine();
	}
	
	
	// Reads an integer from the user with extensive input validation.
	public static int readInteger(String displayString) {
		int numberOfErrors = 0;
		int number = 0;
			
		boolean repeatInput = false;
			
		// Keeps track of the number of times the user puts in incorrectly formatted input.
		do {
			try {
				System.out.print(displayString);
				number = Integer.parseInt(scanner.nextLine());
					
				// Set false only in case the user input is valid. 
				repeatInput = false;
			}
			catch(NumberFormatException e) {
				if(numberOfErrors == 2) {
					System.out.println("\nUser has made too many errors in entering data. Please enter a key to exit.\n");
					scanner.nextLine();
						
					System.exit(0);
				}
				else {
					System.out.println("\nInput must be a valid integer. Try again.\n");
						
					// Set to true to suggest the user has made a mistake and should try again.
					repeatInput = true;
					numberOfErrors++;
				}
			}
		} while (repeatInput);
			
		return number;
	}
	
	// Overloaded to validate for indexes which are out of bounds.
	public static int readInteger(String displayString, int minimum, int maximum) {
		int userInput = 0;
		
		userInput = readInteger(displayString);
		
		// -1 is returned to signify an error in user input. 
		if(userInput < minimum || userInput > maximum) {
			return -1;
		}
		
		return userInput;
	}
	
	// Reads string input for a user and returns it to another part of the application.
	public static String readStringInput(String displayString) {
		String userInput = "";
		
		System.out.print(displayString);
		userInput = scanner.nextLine();
		
		return userInput;
	}

}
