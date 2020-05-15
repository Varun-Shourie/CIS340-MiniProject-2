using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;
using static System.Console;


namespace MP2vshourie
{
    public class LibrarySystem
    {
        // Used in manipulating device characteristics and storing devices for a fully functional library device checkout
        // system.
        private Device tmpDevice;
        private ArrayList deviceList;

        public LibrarySystem()
        {
            tmpDevice = new Device();
            deviceList = new ArrayList();
        }

        // Adds a device object into the list of devices after reading its sku number and name from the user.
        public void AddNewDevice()
        {
            string sku = "";
            string name = "";

            tmpDevice = new Device();

            Utilities.DisplayHeader("Library Device Checkout System - Add New Device");

            sku = Utilities.ReadStringInput("Sku: ").ToUpper();
            name = Utilities.ReadStringInput("Name: ");

            tmpDevice.EditDeviceInformation(sku, name);

            deviceList.Add(tmpDevice);

            Utilities.Pause("\nAdded " + name + " to Catalog.\nPress Enter to continue...");
        }

        // Adds a list of five devices with pre-set vales, thereby preventing unnecessary user input.
        public void AddSampleDevices()
        {
            tmpDevice = new Device("6757A", "Apple 9.7-inch iPad Pro");
            deviceList.Add(tmpDevice);

            tmpDevice = new Device("93P51B", "Amazon Kindle Fire Kids Edition");
            deviceList.Add(tmpDevice);

            tmpDevice = new Device("10N8C", "LeapFrog Epic Learning Tablet");
            deviceList.Add(tmpDevice);

            tmpDevice = new Device("85U20", "Amazon Kindle Fire HD 8");
            tmpDevice.AvailabilityStatus = false;
            deviceList.Add(tmpDevice);

            tmpDevice = new Device("91H2D", "HP Envy 8 Note");
            deviceList.Add(tmpDevice);
        }

        // Allows the user to check in a device from the library system only if it exists and is already checked out. 
        public void CheckInDevice()
        {
            int deviceNumber = 0;
            int userDeviceNumber = 0;
            Device userPreferredDevice = new Device();

            Utilities.DisplayHeader("Library Device Checkout System - Check In Devices\n");

            Write("Checked out Devices\n\n");
            Write("{0,-2} {1,-10} {2,-35}\n", "#", "SKU", "Name");

            // Device number is incremented by one at the beginning to become meaningful to the user.
            foreach(Device d in deviceList)
            {
                deviceNumber++;

                if(!d.AvailabilityStatus)
                    Write("{0,-2} {1,-10} {2,-35}\n", deviceNumber, d.SkuNumber, d.DeviceName);
               
            }

            userDeviceNumber = Utilities.RetrieveDeviceNumber("\nEnter device number: ", deviceList);

            if (userDeviceNumber != -1)
            {
                // Decremented by one to properly access the ArrayList.
                userPreferredDevice = (Device) deviceList[userDeviceNumber - 1];

                if (!userPreferredDevice.AvailabilityStatus)
                {
                    userPreferredDevice.AvailabilityStatus = true;
                    Utilities.Pause("Device Checked In.\n\nPress Enter to continue...");
                }
                else
                    Utilities.Pause("This device is not checked out.\n\nPress Enter to continue...");
            }
            else
            {
                return;
            }
                
            
        }

        // Allows the user to check out a device from the library system only if it exists and is available.
        public void CheckoutDevice()
        {
            int deviceNumber = 0;
            int userDeviceNumber = 0;
            Device userPreferredDevice = new Device();

            Utilities.DisplayHeader("Library Device Checkout System - Check Out Devices\n");

            Write("Available Devices\n\n");
            Write("{0,-2} {1,-10} {2,-35}\n", "#", "SKU", "Name");

            // Device number is incremented before hand to become meaningful to the user. 
            foreach(Device d in deviceList)
            {
                deviceNumber++;

                if (d.AvailabilityStatus)
                    Write("{0,-2} {1,-10} {2,-35}\n", deviceNumber, d.SkuNumber, d.DeviceName);
            }

            userDeviceNumber = Utilities.RetrieveDeviceNumber("\nEnter device number: ", deviceList);

            if (userDeviceNumber != -1)
            {
                // Decremented by one to allow access to the ArrayList. 
                userPreferredDevice = (Device) deviceList[userDeviceNumber - 1];


                if (userPreferredDevice.AvailabilityStatus)
                {
                    userPreferredDevice.AvailabilityStatus = false;
                    Utilities.Pause("Device Checked Out.\n\nPress Enter to continue...");
                }
                else
                    Utilities.Pause("This device is already checked out.\n\nPress Enter to continue...");
            
            }
            else
            {
                return;
            }
                
            
        }

        public ArrayList DeviceList
        {
            get { return DeviceList; }
            set { DeviceList = value; }
        }

        // Displays all devices regardless of availability status.
        private void DisplayAllDevices()
        {
            int deviceNumber = 0;

            Utilities.DisplayHeader("Library Device Checkout System - List\n");
            Write("{0,-2} {1,-10} {2,-35} {3,-11}\n", "#", "SKU", "Name", "Status");

            // device number is incremented at the beginning to become meaningful to the user. 
            foreach(Device d in deviceList) {
                deviceNumber++;

                Write("{0,-2} {1,-10} {2,-35}", deviceNumber, d.SkuNumber, d.DeviceName);

                if (d.AvailabilityStatus)
                    Write(" {0,-11}\n", "Available");
                else
                    Write(" {0,-11}\n", "Checked Out");
            }

        }

        // Simply displays the full list of devices in a manner conducive to a menu. 
        public void DisplayDeviceList()
        {
            DisplayAllDevices();
            Utilities.Pause("\nPress Enter to continue...");
        }

        // Allows the user to alter the device's information only if the device exists.
        public void EditDeviceInformation()
        {
            int numberOfDevices = 0;
            int deviceNumber = 0;
            Device userPreferredDevice = new Device();

            String sku = "";
            String name = "";

            numberOfDevices = GetNumberOfDevices();

            Utilities.DisplayHeader("Library Device Checkout System - Edit Devices\n");

            DisplayAllDevices();

            deviceNumber = Utilities.RetrieveDeviceNumber("\nEnter Device number to edit (" + 1 + "-" + numberOfDevices + "): ",
                deviceList);

            if (deviceNumber != -1)
            {
                sku = Utilities.ReadStringInput("Sku: ").ToUpper();
                name = Utilities.ReadStringInput("Name: ");

                userPreferredDevice = (Device) deviceList[deviceNumber - 1];

                userPreferredDevice.EditDeviceInformation(sku, name);

                Utilities.Pause("\nDevice information updated.\n\nPress Enter to continue...");
            }
            else
            {
                return;
            }
        }

        public void ExitLibrarySystem()
        {
            Write("Good bye!\n");
            Environment.Exit(0);
        }

        // Manually retrieves the number of devices in the ArrayList. 
        public int GetNumberOfDevices()
        {
            int numberOfDevices = 0;

            foreach (Device d in deviceList)
                numberOfDevices++;

            return numberOfDevices;
        }

        // Reads the user choice for which option to execute in the library device checkout system.
        public int ReadLibrarySystemOptions()
        {
            int userMenuChoice = 0;

            Write("\t\tLibrary Device Checkout System\n\n");

            WriteLine("1. List Devices by Title");
            WriteLine("2. Add New Devices");
            WriteLine("3. Edit Device Information");
            WriteLine("4. Search by Device Name");
            WriteLine("5. Check Out Devices");
            WriteLine("6. Check In Devices");
            WriteLine("7. Exit\n\n");

            userMenuChoice = Utilities.ReadInteger("Select menu options 1-7: ", 1, 7);

            return userMenuChoice;
        }

        public void SearchDeviceName()
        {
            string userSearch = "";
            int deviceNumber = 0;
            string deviceName = "";

            Utilities.DisplayHeader("Library Device Checkout System - Search\n");

            userSearch = Utilities.ReadStringInput("Enter Device to search for: ");

            Write("\nListings for '{0}'\n", userSearch);

            Write("{0,-2} {1,-10} {2,-35}\n", "#", "SKU", "Name");

            // Device number is incremented in the beginning to become meaningful to the user.
            // Both the user search and device name are turned into upper case for a case insensitive search. 
            foreach (Device d in deviceList)
            {
                deviceNumber++;

                deviceName = d.DeviceName.ToUpper();

                if (deviceName.Contains(userSearch.ToUpper()))
                    Write("{0,-2} {1,-10} {2,-35}\n", deviceNumber, d.SkuNumber, d.DeviceName);
            }

            Utilities.Pause("\nPress Enter to continue...");
        }
    }
}
