// Varun Shourie, MP2, CIS340, Tuesday/Thursday, 3:00PM-4:15PM

import java.util.ArrayList;

public class LibrarySystem {
	
	// To be used in manipulating device characteristics and storing devices for creating a library
	// device checkout system.
	private Device tmpDevice;
	private ArrayList<Device> deviceList;
	
	public LibrarySystem() {
		tmpDevice = new Device();
		deviceList = new ArrayList<>();
	}
	
	// Adds a device object into the list of devices after reading its sku number and name from the user.
	public void addNewDevice() {
		String sku = "";
		String name = "";
		
		tmpDevice = new Device();
		
		Utilities.displayHeader("Library Device Checkout System - Add New Device");
		
		sku = Utilities.readStringInput("Sku: ").toUpperCase();
		name = Utilities.readStringInput("Name: ");

		tmpDevice.editDeviceInformation(sku, name);
		
		deviceList.add(tmpDevice);
		
		Utilities.pause("\nAdded " + name + " to Catalog.\nPress Enter to continue...");
	}
	
	// Adds a list of five devices with pre-set values, which prevents user input for devices.
	public void addSampleDevices() {
		tmpDevice = new Device("6757A", "Apple 9.7-inch iPad Pro");
		deviceList.add(tmpDevice);
		
		tmpDevice = new Device("93P51B", "Amazon Kindle Fire Kids Edition");
		deviceList.add(tmpDevice);
		
		tmpDevice = new Device("10N8C","LeapFrog Epic Learning Tablet");
		deviceList.add(tmpDevice);
		
		tmpDevice = new Device("85U2O", "Amazon Kindle Fire HD 8");
		tmpDevice.setAvailabilityStatus(false); 
		deviceList.add(tmpDevice);
		
		tmpDevice = new Device("91H2D", "HP Envy 8 Note");
		deviceList.add(tmpDevice);
	}
	
	// Allows a user to check in a device only if it exists and it is checked out already from the library.
	public void checkInDevice() {
		int deviceNumber = 0;
		int userPreferredDevice = 0;
		
		Utilities.displayHeader("Library Device Checkout System - Check In Devices\n");
		
		System.out.println("Checked Out Devices\n");
		System.out.printf("%-2s %-10s %-35s\n", "#", "SKU", "Name");
		
		// Device number is incremented by one to become meaningful to the user.
		for(Device d : deviceList) {
			if(!d.getAvailabilityStatus()) {
				System.out.printf("%-2s %-10s %-35s\n", (deviceNumber+1), d.getSkuNumber(), d.getDeviceName());
			}
			
			deviceNumber++;
		}
		
		// Device number is not incremented since device number contains total number of devices after looping.
		userPreferredDevice = Utilities.readInteger("\nEnter device number: ", 1, (deviceNumber)); 
		
		// The device check in should terminate before the index runs out of bounds when accessing the ArrayList. 
		if(userPreferredDevice == -1) {
			Utilities.pause("Invalid Number.\n\nPress Enter to continue...");
			return;
		}
		
		if(!deviceList.get(userPreferredDevice - 1).getAvailabilityStatus()) {
			deviceList.get(userPreferredDevice - 1).setAvailabilityStatus(true);
			Utilities.pause("Device Checked In.\n\nPress Enter to continue...");
		}
		else {
			Utilities.pause("This device is not checked out.\n\nPress Enter to continue...");
		}
	}
	
	// Allows the user to check out a device from the library system only if it exists and it is available.
	public void checkoutDevice() {
		int deviceNumber = 0;
		int userPreferredDevice = 0;
		
		Utilities.displayHeader("Library Device Checkout System - Check Out Devices\n");
		
		System.out.println("Available Devices\n");
		System.out.printf("%-2s %-10s %-35s\n", "#", "SKU", "Name");
		
		for(Device d : deviceList) {
			if(d.getAvailabilityStatus()) 
				System.out.printf("%-2s %-10s %-35s\n", (deviceNumber+1), d.getSkuNumber(), d.getDeviceName());
			
			deviceNumber++;
		}
		
		// Device number is not incremented by one since it contains total number of devices after looping.
		userPreferredDevice = Utilities.readInteger("\nEnter device number: ", 1, (deviceNumber));
		
		// Before the index can run out of bounds when accessing the ArrayList, the check out should terminate. 
		if(userPreferredDevice == -1) {
			Utilities.pause("Invalid Number.\n\nPress Enter to continue...");
			return;
		}
		
		if(deviceList.get(userPreferredDevice - 1).getAvailabilityStatus()) {
			deviceList.get(userPreferredDevice - 1).setAvailabilityStatus(false);
			Utilities.pause("Device Checked Out.\n\nPress Enter to continue...");
		}
		else {
			Utilities.pause("This device is already checked out.\n\nPress Enter to continue...");
		}
		
	}
	
	// Displays the list of devices that are available/not available. 
	public void displayAllDevices() {
		int deviceNumber = 0;
		
		Utilities.displayHeader("Library Device Checkout System - List\n");
		System.out.printf("%-2s %-10s %-35s %-11s\n", "#", "SKU", "Name", "Status");
		
		// deviceNumber is incremented by one to become meaningful to the user. 
		for(Device d : getDeviceList()) {
			System.out.printf("%-2d %-10s %-35s", (deviceNumber+1), d.getSkuNumber(), d.getDeviceName());
			
			if(d.getAvailabilityStatus()) {
				System.out.printf(" %-11s\n", "Available");
			}
			else if(!d.getAvailabilityStatus()) {
				System.out.printf(" %-11s\n", "Checked Out");
			}
			
			deviceNumber++;
		}
	}
	
	
	
	// Displays the full list of devices in a manner conducive to the menu. 
	public void displayDeviceList() {
		displayAllDevices();
		System.out.println();
		
		Utilities.pause("Press Enter to continue...");
	}
	
	// Allows the user to alter the device's information if the device even exists. 
	public void editDeviceInformation() {
		int numberOfDevices = 0;
		int userChoice = 0;
		
		String sku = "";
		String name = "";
		
		numberOfDevices = getNumberOfDevices();
		
		Utilities.displayHeader("Library Device Checkout System - Edit Devices\n");
		
		displayAllDevices();
		
		userChoice = Utilities.readInteger("\nEnter Device number to edit (" + 1 + "-" + numberOfDevices + "): ",
				1, numberOfDevices);
		
		if(userChoice != -1) {
			sku = Utilities.readStringInput("Sku: ").toUpperCase();
			name = Utilities.readStringInput("Name: ");
			
			// The user's preferred device number to alter is decremented by one to properly access the ArrayList. 
			deviceList.get(userChoice - 1).editDeviceInformation(sku, name);
			
			Utilities.pause("\nDevice information updated.\n\nPress Enter to continue...");
		}
		else {
			Utilities.pause("Invalid Number.\n\nPress Enter to continue...");
		}
		
	}
	
	// Exits the library system for the user upon being invoked.
	public void exitLibrarySystem() {
		System.out.println("Good bye!");
		System.exit(0);
	}
	
	public ArrayList<Device> getDeviceList() {
		return deviceList;
	}
	
	// Since the ArrayList cannot use counter variables, we must manually retrieve the number of devices
	// by recreating the counter variable with a loop. 
	public int getNumberOfDevices() {
		int numberOfDevices = 0;
		
		for(Device d : deviceList) {
			numberOfDevices++;
		}
		
		return numberOfDevices;
	}
	
	// Reads the user's choice regarding which option to execute in the library device checkout system.
	public int readMenuChoice() {
		int userMenuChoice = 0;
		
		System.out.println("\t\tLibrary Device Checkout System\n");
		
		System.out.println("1. List Devices by Title");
		System.out.println("2. Add New Devices");
		System.out.println("3. Edit Device Information");
		System.out.println("4. Search by Device Name");
		System.out.println("5. Check Out Devices");
		System.out.println("6. Check In Devices");
		System.out.println("7. Exit\n\n");
		
		userMenuChoice = Utilities.readInteger("Select menu options 1-7: ");
		return userMenuChoice;
	}
	
	// Searches for devices matching user input and displays the results. 
	public void searchDeviceName() {
		String userSearch = "";
		int deviceNumber = 0;
		String deviceName = "";
		
		Utilities.displayHeader("Library Device Checkout System - Search\n");
		
		userSearch = Utilities.readStringInput("Enter Device to search for: ");
		
		System.out.printf("\nListings for '%s'\n", userSearch);
		
		System.out.printf("%-2s %-10s %-35s\n", "#", "SKU", "Name");
		
		// Performs a case insensitive comparison to search for the user input.
		for(Device d : deviceList) {
			deviceName = d.getDeviceName().toUpperCase();
			
			if(deviceName.contains(userSearch.toUpperCase()))
				System.out.printf("%-2s %-10s %-35s\n", (deviceNumber+1), d.getSkuNumber(), d.getDeviceName());
		}
		
		Utilities.pause("\nPress Enter to continue...");
	}
	
	public void setDeviceList(ArrayList<Device> deviceList) {
		this.deviceList = deviceList;
	}
}
