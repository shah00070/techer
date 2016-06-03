package com.schoolteacher.pojos;

import java.util.List;

public class ProfessionalProfileAppointment {
	private int Id;
	private String PublicId;
	private String Title;
	private String FirstName;
	private String LastName;
	private String Fees;
	private String Name;
	private String ProfilePictureURL;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getPublicId() {
		return PublicId;
	}

	public void setPublicId(String publicId) {
		PublicId = publicId;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
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

	public String getFees() {
		return Fees;
	}

	public void setFees(String fees) {
		Fees = fees;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getProfilePictureURL() {
		return ProfilePictureURL;
	}

	public void setProfilePictureURL(String profilePictureURL) {
		ProfilePictureURL = profilePictureURL;
	}

	public List<ProfileFacility> getFacilityProfiles() {
		return FacilityProfiles;
	}

	public void setFacilityProfiles(List<ProfileFacility> facilityProfiles) {
		FacilityProfiles = facilityProfiles;
	}

	private List<ProfileFacility> FacilityProfiles;

}
