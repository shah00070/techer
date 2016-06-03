package com.schoolteacher.pojos;

public class ToFacilityDetails {
	private int Id;
	private String PublicId;
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

	public Address getAddress() {
		return Address;
	}

	public void setAddress(Address address) {
		Address = address;
	}

	public int getYearFounded() {
		return YearFounded;
	}

	public void setYearFounded(int yearFounded) {
		YearFounded = yearFounded;
	}

	public int getBusinessOwnerMemberId() {
		return BusinessOwnerMemberId;
	}

	public void setBusinessOwnerMemberId(int businessOwnerMemberId) {
		BusinessOwnerMemberId = businessOwnerMemberId;
	}

	private Address Address;
	// private object ServiceConfigurations ;
	// private object Sessions ;
	// private object ServiceRequisions ;
	// private List<object> ProfileCategories ;
	// private List<object> ProfileSettingDetails ;
	private int YearFounded;
	// private object Description ;
	// private object Email ;
	// private object CellNumber ;
	// private object Features ;
	// private object ServicesOffered ;
	// private object LogoUrl ;
	private int BusinessOwnerMemberId;
	// private object BusinessOwnerProfilePic ;
	// private object ProfessionalProfiles ;
}
