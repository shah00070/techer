package com.schoolteacher.mylibrary.model;

import java.io.Serializable;

public class MemberInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String Email;
	private String Password;
	private String FailureReason;
	private String FirstName;
	private String LastName;
	private String CellNumber;
	private String DateOfBirth;
	private String Gender;
	private String Title;
	private boolean IsAutogeneratePassword;
	private int UserId;
	private boolean IsVoulantryBloodDonation;
	private int BloodGroupType;
	private String State;
	private String City;
	private String Country;
	private String Area;
	private String ZipCode;
	private String AddressLine1;
	private String AddressLine2;
	private double longitude;
	private double latitude;
	private boolean isFacilityOwner;
	private boolean isHealthCareProfessional;
	private boolean IsEmailVerified;
	private boolean IsPhoneVerified;

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPasswordHash() {
		return Password;
	}

	public void setPasswordHash(String passwordHash) {
		Password = passwordHash;
	}

	public String getFailureReason() {
		return FailureReason;
	}

	public void setFailureReason(String failureReason) {
		FailureReason = failureReason;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getMobilenumber() {
		return CellNumber;
	}

	public void setMobilenumber(String mobilenumber) {
		CellNumber = mobilenumber;
	}

	public String getDateOfBirth() {
		return DateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public boolean isIsAutogeneratePassword() {
		return IsAutogeneratePassword;
	}

	public void setIsAutogeneratePassword(boolean isAutogeneratePassword) {
		IsAutogeneratePassword = isAutogeneratePassword;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public boolean isIsVoulantryBloodDonation() {
		return IsVoulantryBloodDonation;
	}

	public void setIsVoulantryBloodDonation(boolean isVoulantryBloodDonation) {
		IsVoulantryBloodDonation = isVoulantryBloodDonation;
	}

	public int getBloodGroupType() {
		return BloodGroupType;
	}

	public void setBloodGroupType(int bloodGroupType) {
		BloodGroupType = bloodGroupType;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getArea() {
		return Area;
	}

	public void setArea(String area) {
		Area = area;
	}

	public String getZipCode() {
		return ZipCode;
	}

	public void setZipCode(String zipCode) {
		ZipCode = zipCode;
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

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public boolean isFacilityOwner() {
		return isFacilityOwner;
	}

	public void setFacilityOwner(boolean isFacilityOwner) {
		this.isFacilityOwner = isFacilityOwner;
	}

	public boolean isHealthCareProfessional() {
		return isHealthCareProfessional;
	}

	public void setHealthCareProfessional(boolean isHealthCareProfessional) {
		this.isHealthCareProfessional = isHealthCareProfessional;
	}

	public boolean isIsEmailVerified() {
		return IsEmailVerified;
	}

	public void setIsEmailVerified(boolean isEmailVerified) {
		IsEmailVerified = isEmailVerified;
	}

	public boolean isIsPhoneVerified() {
		return IsPhoneVerified;
	}

	public void setIsPhoneVerified(boolean isPhoneVerified) {
		IsPhoneVerified = isPhoneVerified;
	}
}
