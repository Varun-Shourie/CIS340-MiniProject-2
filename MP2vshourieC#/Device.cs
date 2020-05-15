using System;

namespace MP2vshourie
{
    public class Device
    {

        // The three aspects of a device which the library device checkout system will track.
        private string skuNumber;
        private string deviceName;
        private bool availabilityStatus;

        // For the three properties present, I chose not to abbreviate it further since this likely allows for easier maintenance.
        public bool AvailabilityStatus
        {
            get { return availabilityStatus; }
            set { availabilityStatus = value; }
        }

        // By default, we assume the device is automatically available when added.
        public Device(string skuNumber = "", string deviceName = "")
        {
            SkuNumber = skuNumber;
            DeviceName = deviceName;
            AvailabilityStatus = true;
        }

        public string DeviceName
        {
            get { return deviceName; }
            set { deviceName = value; }
        }

        public void EditDeviceInformation(string skuNumber, string deviceName)
        {
            SkuNumber = skuNumber;
            DeviceName = deviceName;
        }

        public string SkuNumber
        {
            get { return skuNumber; }
            set { skuNumber = value; }
        }

    }
}
