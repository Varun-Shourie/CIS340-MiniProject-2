// Varun Shourie, MP2, CIS340, Tuesday/Thursday, 3:00PM-4:15PM

public class Device {
	
	// Characteristics of an electronic device of interest to the user.
	private String skuNumber;
	private String deviceName;
	private boolean availabilityStatus;
	
	// We assume the device is automatically available by default.
	public Device() {
		availabilityStatus = true;
	}
	
	// Same logic as above - the device is automatically available by default.
	public Device(String skuNumber, String deviceName) {
		availabilityStatus = true;
		this.skuNumber = skuNumber;
		this.deviceName = deviceName;
	}
	
	// Allows the device object to edit its own information.
	public void editDeviceInformation(String skuNumber, String deviceName) {
		setSkuNumber(skuNumber);
		setDeviceName(deviceName);
	}
	
	public boolean getAvailabilityStatus() {
		return availabilityStatus;
	}
	
	public String getDeviceName() {
		return deviceName;
	}
	
	public String getSkuNumber() {
		return skuNumber;
	}
	
	public void setAvailabilityStatus(boolean availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}
	
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	public void setSkuNumber(String skuNumber) {
		this.skuNumber = skuNumber;
	}
}
