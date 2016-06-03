package com.schoolteacher.pojos;

import java.util.List;

public class LabTests {
	private String IsTestAtHome;
	private String TimePreference;
	private Address DeliveryAddress;

	public String getIsTestAtHome() {
		return IsTestAtHome;
	}

	public void setIsTestAtHome(String isTestAtHome) {
		IsTestAtHome = isTestAtHome;
	}

	public String getTimePreference() {
		return TimePreference;
	}

	public void setTimePreference(String timePreference) {
		TimePreference = timePreference;
	}

	public Address getDeliveryAddress() {
		return DeliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		DeliveryAddress = deliveryAddress;
	}

	public List<Test> getTest() {
		return Test;
	}

	public void setTest(List<Test> test) {
		Test = test;
	}

	private List<Test> Test;
}
