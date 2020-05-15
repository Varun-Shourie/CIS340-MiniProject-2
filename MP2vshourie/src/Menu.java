// Varun Shourie, MP2, CIS340, Tuesday/Thursday, 3:00PM-4:15PM

public class Menu {
	
	// We only require a library system to execute the program.
	private LibrarySystem librarySystem;
	
	public Menu() {
		librarySystem = new LibrarySystem();
	}
	
	// Displays the menu repeatedly until the user opts out of the library device checkout system.
	private void displayMenu() {
		
		boolean repeatInput = false;
		int userMenuChoice = 0;
		int numberOfErrors = 0;		
		
		librarySystem.addSampleDevices();
		
		do {			
			
			repeatInput = true;
			
			userMenuChoice = librarySystem.readMenuChoice();
			
			// The user finally entered correct input, and thus deserves 3 extra chances for bad numeric input. 
			if(userMenuChoice >= 1 && userMenuChoice <= 6) {
				Utilities.insertFiveBlankLines();
				numberOfErrors = 0;
			}
			
			// As per the prompted menu choices, the user's selection results in the option's execution.
			switch(userMenuChoice) {
			case 1:
				librarySystem.displayDeviceList();
				Utilities.insertFiveBlankLines();
				break;
			case 2:
				librarySystem.addNewDevice();
				Utilities.insertFiveBlankLines();
				break;
			case 3:
				librarySystem.editDeviceInformation();
				Utilities.insertFiveBlankLines();
				break;
			case 4: 
				librarySystem.searchDeviceName();
				Utilities.insertFiveBlankLines();
				break;
			case 5:
				librarySystem.checkoutDevice();
				Utilities.insertFiveBlankLines();
				break;
			case 6:
				librarySystem.checkInDevice();
				Utilities.insertFiveBlankLines();
				break;
			case 7:
				librarySystem.exitLibrarySystem();
				Utilities.insertFiveBlankLines();
				repeatInput = false;
				break;
				
			// This case keeps the application stable while limiting the instances of bad numeric input to 3 times.
			default:
				if(numberOfErrors == 2) {
					System.out.println("\nUser has made too many errors in entering data. Please enter a key to exit.");
					Utilities.getScanner().nextLine();
						
					System.exit(0);
				}
				
				numberOfErrors++;
				
				Utilities.insertFiveBlankLines();
			}
			
		} while (repeatInput);
	}
	
	// Physically executes the program.
	public static void main(String[] args) {
		
		Menu menu = new Menu();
		
		menu.displayMenu();
		
		Utilities.getScanner().close();
	}
	
}
