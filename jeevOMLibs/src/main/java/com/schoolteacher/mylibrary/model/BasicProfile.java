package com.schoolteacher.mylibrary.model;

import java.io.Serializable;

public class BasicProfile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String FirstName;
	private String LastName;
	// private String Gender;
	// private String DateOfBirth;
	// private String Occupation;
	// private String AboutMe;
	private String Photo;
   private String PracticeSince ;
	// private String BloodGroup;
	// private String ContactInformation;
	private String Experience ;
	private String EducationDetails;
	private String Services;
	private String Expertise;
	private String AwardsAndMentions;
	private String OtherInformation;
	private String Title;

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

	public String getPhoto() {
		return Photo;
	}

	public void setPhoto(String photo) {
		Photo = photo;
	}

	public String getEducationDetails() {
		return EducationDetails;
	}

	public void setEducationDetails(String educationDetails) {
		EducationDetails = educationDetails;
	}

	public String getAwardsAndMentions() {
		return AwardsAndMentions;
	}

	public void setAwardsAndMentions(String awardsAndMentions) {
		AwardsAndMentions = awardsAndMentions;
	}

	public String getOtherInformation() {
		return OtherInformation;
	}

	public void setOtherInformation(String otherInformation) {
		OtherInformation = otherInformation;
	}

	public String getServices() {
		return Services;
	}

	public void setServices(String services) {
		Services = services;
	}

	public String getExpertise() {
		return Expertise;
	}

	public void setExpertise(String expertise) {
		Expertise = expertise;
	}

	public String getExperience() {
		return Experience;
	}

	public void setExperience(String experience) {
		Experience = experience;
	}

	public String getPracticeSince() {
		return PracticeSince;
	}

	public void setPracticeSince(String practiceSince) {
		PracticeSince = practiceSince;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}
}
