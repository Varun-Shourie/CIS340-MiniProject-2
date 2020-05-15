using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;
using static System.Console;

namespace MP2vshourie
{
    class Utilities
    {

        public static void DisplayHeader(string displayString)
        {
            Write("\t\t{0}\n\n", displayString);
        }

        public static void InsertFiveBlankLines()
        {
            Write("\n\n\n\n\n");
        }

        public static void Pause(string displayString)
        {
            Write(displayString);
            ReadLine();
        }

        // Reads a user inputted number and checks only whether if it is an integer or not.
        public static int ReadInteger(string displayString)
        {
            int numberOfErrors = 0;
            int number = 0;
            Device testDevice = new Device();

            bool repeatInput = false;

            // Keeps track of the number of times a user inputs incorrectly formatted input.
            do
            {
                try
                {
                    Write(displayString);
                    number = Convert.ToInt16(ReadLine());

                    // Set false only in case the input is valid.
                    repeatInput = false;
                }
                catch (FormatException fe)
                {
                    if (numberOfErrors == 2)
                    {
                        Write("\nUser has made too many errors in entering data. Please enter a key to exit. \n");
                        ReadLine();

                        Environment.Exit(0);
                    }
                    else
                    {
                        Write("\nInput must be a valid integer. Try again. \n\n");

                        // Set to true to suggest the user has made a mistake and should try again.
                        repeatInput = true;

                        numberOfErrors++;
                    }
                }
                catch(Exception e)
                {
                    if (numberOfErrors == 2)
                    {
                        Write("\nUser has made too many errors in entering data. Please enter a key to exit. \n");
                        ReadLine();

                        Environment.Exit(0);
                    }
                    else
                    {
                        Write("\nInput must be a valid integer. Try again. \n\n");

                        // Set to true to suggest the user has made a mistake and should try again.
                        repeatInput = true;

                        numberOfErrors++;
                    }
                }

            } while (repeatInput);

            return number;
        }

        // Overloaded solely for the purpose of menu input validation.
        public static int ReadInteger(string displayString, int minimum, int maximum)
        {
            int number = 0;  
            
            number = ReadInteger(displayString);

            if (number >= minimum && number <= maximum)
                return number;
            else
                return -1;
        }

        public static string ReadStringInput(string displayString)
        {
            string userInput = "";

            Write(displayString);
            userInput = ReadLine();

            return userInput;
        }

        // Returns a specific device from the ArrayList after testing whether if it can be accessed.
        public static int RetrieveDeviceNumber(string displayString, ArrayList deviceList)
        {
            int number = 0;
            Device tmpDevice = new Device();

            number = ReadInteger(displayString);

            // Device number is decremented by one to properly access the ArrayList. 
            try
            {
                tmpDevice = (Device) deviceList[number - 1];
                return number;
            }
            catch (ArgumentOutOfRangeException aore)
            {
                Pause("\nInvalid Number.\n\nPress Enter to continue...");
                return -1;
            }
            catch(Exception e)
            {
                Pause("\nInvalid Number.\n\nPress Enter to continue...");
                return -1;
            }
            
        }

    }
}
