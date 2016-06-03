package com.schoolteacher.mylibrary.model;

import java.io.Serializable;

public class ContactInformation implements Serializable {

	private static final long serialVersionUID = 1L;
	private Address Address;
	private String PrimaryPhone;
	private int Id;
	private int FacilityID;
	private String SecondaryPhone;
	private String TertiaryPhone;
	private String SecondaryEmail;
	private String TertiaryEmail;
	private boolean IsPrimary;
	private String Fees;
	private String FacilityName;
	private String Availability;
	private String ServicesOffered;

	public String getPrimaryPhone() {
		return PrimaryPhone;
	}

	public void setPrimaryPhone(String primaryPhone) {
		PrimaryPhone = primaryPhone;
	}

	public String getSecondaryPhone() {
		return SecondaryPhone;
	}

	public void setSecondaryPhone(String secondaryPhone) {
		SecondaryPhone = secondaryPhone;
	}

	public String getTertiaryPhone() {
		return TertiaryPhone;
	}

	public void setTertiaryPhone(String tertiaryPhone) {
		TertiaryPhone = tertiaryPhone;
	}

	public String getSecondaryEmail() {
		return SecondaryEmail;
	}

	public void setSecondaryEmail(String secondaryEmail) {
		SecondaryEmail = secondaryEmail;
	}

	public String getTertiaryEmail() {
		return TertiaryEmail;
	}

	public void setTertiaryEmail(String tertiaryEmail) {
		TertiaryEmail = tertiaryEmail;
	}

	public Address getAddress() {
		return Address;
	}

	public void setAddress(Address address) {
		Address = address;
	}

	public boolean isIsPrimary() {
		return IsPrimary;
	}

	public void setIsPrimary(boolean isPrimary) {
		IsPrimary = isPrimary;
	}

	public String getFees() {
		return Fees;
	}

	public void setFees(String fees) {
		Fees = fees;
	}

	public String getFacilityName() {
		return FacilityName;
	}

	public void setFacilityName(String facilityName) {
		FacilityName = facilityName;
	}

	public String getAvailability() {
		return Availability;
	}

	public void setAvailability(String availability) {
		Availability = availability;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getFacilityID() {
		return FacilityID;
	}

	public void setFacilityID(int facilityID) {
		FacilityID = facilityID;
	}

	public String getServicesOffered() {
		return ServicesOffered;
	}

	public void setServicesOffered(String servicesOffered) {
		ServicesOffered = servicesOffered;
	}

}
