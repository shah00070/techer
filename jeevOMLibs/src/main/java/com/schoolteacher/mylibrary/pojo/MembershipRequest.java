package com.schoolteacher.mylibrary.pojo;

public class MembershipRequest {
	private String Title;

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

	public String getCellnumber() {
		return cellnumber;
	}

	public void setCellnumber(String cellnumber) {
		this.cellnumber = cellnumber;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
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

	public ContactInformation getContactInformation() {
		return ContactInformation;
	}

	public void setContactInformation(ContactInformation contactInformation) {
		ContactInformation = contactInformation;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public boolean isHealthCareProfessional() {
		return isHealthCareProfessional;
	}

	public void setHealthCareProfessional(boolean isHealthCareProfessional) {
		this.isHealthCareProfessional = isHealthCareProfessional;
	}

	public boolean isFacilityOwner() {
		return isFacilityOwner;
	}

	public void setFacilityOwner(boolean isFacilityOwner) {
		this.isFacilityOwner = isFacilityOwner;
	}

	public boolean isIsAutogeneratePassword() {
		return IsAutogeneratePassword;
	}

	public void setIsAutogeneratePassword(boolean isAutogeneratePassword) {
		IsAutogeneratePassword = isAutogeneratePassword;
	}

	private String FirstName;
	private String LastName;
	private String cellnumber;
	private String Email;
	private String DateOfBirth;
	private String Gender;
	private int BloodGroupType;
	private boolean IsVoulantryBloodDonation;

	private ContactInformation ContactInformation;
	private String Password;
	private boolean isHealthCareProfessional;
	private boolean isFacilityOwner;
	private boolean IsAutogeneratePassword;
}
