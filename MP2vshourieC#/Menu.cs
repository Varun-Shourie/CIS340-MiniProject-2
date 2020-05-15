using System;
using System.Collections.Generic;
using System.Text;
using static System.Console;

namespace MP2vshourie
{
    public class Menu
    {

        // We only require a library system to execute the menu.
        private LibrarySystem librarySystem;

        public Menu()
        {
            librarySystem = new LibrarySystem();
        }

        // Displays the menu repeatedly until the user opts out of the library device checkout system.
        private void DisplayMenu()
        {
            bool repeatInput = false;
            int userMenuChoice = 0;
            int numberOfErrors = 0;

            librarySystem.AddSampleDevices();

            do
            {
                repeatInput = true;

                userMenuChoice = librarySystem.ReadLibrarySystemOptions();

                // Resets the number of errors if the user entered correct input.
                if (userMenuChoice >= 1 && userMenuChoice <= 6)
                {
                    Utilities.InsertFiveBlankLines();
                    numberOfErrors = 0;
                }

                // The user's selection in the menu results in the option's execution from the menu.
                switch (userMenuChoice)
                {
                    case 1:
                        librarySystem.DisplayDeviceList();
                        Utilities.InsertFiveBlankLines();
                        break;
                    case 2:
                        librarySystem.AddNewDevice();
                        Utilities.InsertFiveBlankLines();
                        break;
                    case 3:
                        librarySystem.EditDeviceInformation();
                        Utilities.InsertFiveBlankLines();
                        break;
                    case 4:
                        librarySystem.SearchDeviceName();
                        Utilities.InsertFiveBlankLines();
                        break;
                    case 5:
                        librarySystem.CheckoutDevice();
                        Utilities.InsertFiveBlankLines();
                        break;
                    case 6:
                        librarySystem.CheckInDevice();
                        Utilities.InsertFiveBlankLines();
                        break;
                    case 7:
                        librarySystem.ExitLibrarySystem();
                        break;
                    default:
                        if(numberOfErrors == 2)
                        {
                            WriteLine("\nUser has made too many errors in entering data. Please enter a key to exit.");
                            ReadLine();

                            Environment.Exit(0);
                        }

                        numberOfErrors++;

                        Utilities.InsertFiveBlankLines();

                        break;
                }
            } while (repeatInput);
        }

        public static void Main(string[] args)
        {
            Menu menu = new Menu();

            menu.DisplayMenu();

        }
    }
}
