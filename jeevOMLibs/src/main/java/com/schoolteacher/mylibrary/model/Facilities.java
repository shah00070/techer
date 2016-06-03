package com.schoolteacher.mylibrary.model;

import java.io.Serializable;
import java.util.List;

public class Facilities implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Long> UserIdList;
	private int Id;
	private String FacilityName;
	private int FacilityType;
	private String PrimaryPhone;
	private String SecondryPhone;
	private String TertiaryPhone;
	private String PrimaryEmail;
	private String SecondryEmail;
	private String TertiaryEmail;
	private String AddressLine1;
	private String AddressLine2;
	private String Area;
	private String City;
	private String State;
	private String Country;
	private String Fees;
	private String ZipCode;
	private String Availability;
	private String ServicesOffered;
	// private String AwardsAndMentions ;
	// private String PanelMembersDetails ;
	// private String OtherInformation ;

	public List<Long> getUserIdList() {
		return UserIdList;
	}

	public void setUserIdList(List<Long> userIdList) {
		UserIdList = userIdList;
	}

	public String getFacilityName() {
		return FacilityName;
	}

	public void setFacilityName(String facilityName) {
		FacilityName = facilityName;
	}

	public int getFacilityType() {
		return FacilityType;
	}

	public void setFacilityType(int facilityType) {
		FacilityType = facilityType;
	}

	public String getPrimaryPhone() {
		return PrimaryPhone;
	}

	public void setPrimaryPhone(String primaryPhone) {
		PrimaryPhone = primaryPhone;
	}

	public String getPrimaryEmail() {
		return PrimaryEmail;
	}

	public void setPrimaryEmail(String primaryEmail) {
		PrimaryEmail = primaryEmail;
	}

	public String getAddressLine1() {
		return AddressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		AddressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return AddressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		AddressLine2 = addressLine2;
	}

	public String getArea() {
		return Area;
	}

	public void setArea(String area) {
		Area = area;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getServicesOffered() {
		return ServicesOffered;
	}

	public void setServicesOffered(String servicesOffered) {
		ServicesOffered = servicesOffered;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSecondryPhone() {
		return SecondryPhone;
	}

	public void setSecondryPhone(String secondryPhone) {
		SecondryPhone = secondryPhone;
	}

	public String getSecondryEmail() {
		return SecondryEmail;
	}

	public void setSecondryEmail(String secondryEmail) {
		SecondryEmail = secondryEmail;
	}

	public String getZipCode() {
		return ZipCode;
	}

	public void setZipCode(String zipCode) {
		ZipCode = zipCode;
	}

	public String getAvailability() {
		return Availability;
	}

	public void setAvailability(String availability) {
		Availability = availability;
	}

	public String getTertiaryPhone() {
		return TertiaryPhone;
	}

	public void setTertiaryPhone(String tertiaryPhone) {
		TertiaryPhone = tertiaryPhone;
	}

	public String getTertiaryEmail() {
		return TertiaryEmail;
	}

	public void setTertiaryEmail(String tertiaryEmail) {
		TertiaryEmail = tertiaryEmail;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getFees() {
		return Fees;
	}

	public void setFees(String fees) {
		Fees = fees;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

}
