package com.schoolteacher.mylibrary.pojo;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class MembershipAuthenticateProfile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	private String FirstName;
	@Expose
	private String LastName;
	@Expose
	private String Gender;
	@Expose
	private String DateOfBirth;
	@Expose
	private String Occupation;
	@Expose
	private String AboutMe;
	@Expose
	private String Photo;
	@Expose
	private String ContactInformation;
	@Expose
	private String BloodGroup;
	@Expose
	private int BloodGroupType;
	@Expose
	private boolean IsVoulantryBloodDonation;

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

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getDateOfBirth() {
		return DateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}

	public String getOccupation() {
		return Occupation;
	}

	public void setOccupation(String occupation) {
		Occupation = occupation;
	}

	public String getAboutMe() {
		return AboutMe;
	}

	public void setAboutMe(String aboutMe) {
		AboutMe = aboutMe;
	}

	public String getPhoto() {
		return Photo;
	}

	public void setPhoto(String photo) {
		Photo = photo;
	}

	public String getContactInformation() {
		return ContactInformation;
	}

	public void setContactInformation(String contactInformation) {
		ContactInformation = contactInformation;
	}

	public String getBloodGroup() {
		return BloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		BloodGroup = bloodGroup;
	}

	public int getBloodGroupType() {
		return BloodGroupType;
	}

	public void setBloodGroupType(int bloodGroupType) {
		BloodGroupType = bloodGroupType;
	}

	public boolean isIsVoulantryBloodDonation() {
		return IsVoulantryBloodDonation;
	}

	public void setIsVoulantryBloodDonation(boolean isVoulantryBloodDonation) {
		IsVoulantryBloodDonation = isVoulantryBloodDonation;
	}

}
