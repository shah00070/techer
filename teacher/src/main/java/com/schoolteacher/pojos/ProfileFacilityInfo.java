package com.schoolteacher.pojos;

public class ProfileFacilityInfo {
	private int Id;
	private String PublicId;
	private String Name;
	private String ProfilePictureURL;
	private JeevProfileAddress Address;

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

	public JeevProfileAddress getAddress() {
		return Address;
	}

	public void setAddress(JeevProfileAddress address) {
		Address = address;
	}
}
