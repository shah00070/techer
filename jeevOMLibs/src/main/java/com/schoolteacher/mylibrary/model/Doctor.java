package com.schoolteacher.mylibrary.model;

import java.io.Serializable;
import java.util.List;

public class Doctor implements Serializable {

	private static final long serialVersionUID = 1L;
	private int UniquePublicID;
	private boolean IsDeleted;
	private String Name;
	private int CreatedBy;
	private int UpdatedBy;
	private int Id;
	private String CreatedOnUTC;
	private String UpdatedOnUTC;
	private List<ContactInformation> ContactInformations;
	private List<String> Specialities;
	private BasicProfile BasicProfile;
	private List<String> EducationalQualifications;
	private List<SocialMediaInformation> SocialMediaInformations;
	private List<PhotoGallary> PhotoGallary;
	private String FullName;
	private String Email;
	private String Rank;
	private String Services;
	private String Description;
	private String ProfilePhoto;
	private FacilityType FacilityType;
	private String AwardsAndMentions;
	private String PanelMembersDetails;
	private String OtherInformation;
	private boolean IsClaimed;

	// Doctor SignUp Fields
	private String FirstName;
	private String LastName;
	private String Mobilenumber;
	private String DateOfBirth;
	private String Gender;
	private int PracticeSince;
	private int UserSubTypeId;
	private RegistrationDetails RegistrationDetails;
	private List<String> Degrees;
	private String PasswordHash;
	private int BloodGroupType;
	private boolean IsVoulantryBloodDonation;

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


	// Doctor SignUp Fields End

	public int getUniquePublicID() {
		return UniquePublicID;
	}

	public void setUniquePublicID(int uniquePublicID) {
		UniquePublicID = uniquePublicID;
	}

	public boolean isIsDeleted() {
		return IsDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		IsDeleted = isDeleted;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(int createdBy) {
		CreatedBy = createdBy;
	}

	public int getUpdatedBy() {
		return UpdatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		UpdatedBy = updatedBy;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getCreatedOnUTC() {
		return CreatedOnUTC;
	}

	public void setCreatedOnUTC(String createdOnUTC) {
		CreatedOnUTC = createdOnUTC;
	}

	public String getUpdatedOnUTC() {
		return UpdatedOnUTC;
	}

	public void setUpdatedOnUTC(String updatedOnUTC) {
		UpdatedOnUTC = updatedOnUTC;
	}

	public List<ContactInformation> getContactInformations() {
		return ContactInformations;
	}

	public void setContactInformations(List<ContactInformation> contactInformations) {
		ContactInformations = contactInformations;
	}

	public List<String> getSpecialities() {
		return Specialities;
	}

	public void setSpecialities(List<String> specialities) {
		Specialities = specialities;
	}

	public BasicProfile getBasicProfile() {
		return BasicProfile;
	}

	public void setBasicProfile(BasicProfile basicProfile) {
		BasicProfile = basicProfile;
	}

	public List<String> getEducationalQualifications() {
		return EducationalQualifications;
	}

	public void setEducationalQualifications(List<String> educationalQualifications) {
		EducationalQualifications = educationalQualifications;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getRank() {
		return Rank;
	}

	public void setRank(String rank) {
		Rank = rank;
	}

	public List<SocialMediaInformation> getSocialMediaInformations() {
		return SocialMediaInformations;
	}

	public void setSocialMediaInformations(List<SocialMediaInformation> socialMediaInformations) {
		SocialMediaInformations = socialMediaInformations;
	}

	public List<PhotoGallary> getPhotoGallary() {
		return PhotoGallary;
	}

	public void setPhotoGallary(List<PhotoGallary> photoGallary) {
		PhotoGallary = photoGallary;
	}

	public FacilityType getFacilityType() {
		return FacilityType;
	}

	public void setFacilityType(FacilityType facilityType) {
		FacilityType = facilityType;
	}

	public String getProfilePhoto() {
		return ProfilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		ProfilePhoto = profilePhoto;
	}

	public String getServices() {
		return Services;
	}

	public void setServices(String services) {
		Services = services;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getAwardsAndMentions() {
		return AwardsAndMentions;
	}

	public void setAwardsAndMentions(String awardsAndMentions) {
		AwardsAndMentions = awardsAndMentions;
	}

	public String getPanelMembersDetails() {
		return PanelMembersDetails;
	}

	public void setPanelMembersDetails(String panelMembersDetails) {
		PanelMembersDetails = panelMembersDetails;
	}

	public String getOtherInformation() {
		return OtherInformation;
	}

	public void setOtherInformation(String otherInformation) {
		OtherInformation = otherInformation;
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

	public String getMobileNumber() {
		return getMobilenumber();
	}

	public void setMobileNumber(String mobileNumber) {
		setMobilenumber(mobileNumber);
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

	public RegistrationDetails getRegistrationDetails() {
		return RegistrationDetails;
	}

	public void setRegistrationDetails(RegistrationDetails registrationDetails) {
		RegistrationDetails = registrationDetails;
	}

	public List<String> getDegrees() {
		return Degrees;
	}

	public void setDegrees(List<String> degrees) {
		Degrees = degrees;
	}

	public String getPasswordHash() {
		return PasswordHash;
	}

	public void setPasswordHash(String passwordHash) {
		PasswordHash = passwordHash;
	}

	public boolean isIsClaimed() {
		return IsClaimed;
	}

	public void setIsClaimed(boolean isClaimed) {
		IsClaimed = isClaimed;
	}

	public int getPracticeSince() {
		return PracticeSince;
	}

	public void setPracticeSince(int practiceSince) {
		PracticeSince = practiceSince;
	}

	public int getUserSubTypeId() {
		return UserSubTypeId;
	}

	public void setUserSubTypeId(int userSubTypeId) {
		UserSubTypeId = userSubTypeId;
	}

	public String getMobilenumber() {
		return Mobilenumber;
	}

	public void setMobilenumber(String mobilenumber) {
		Mobilenumber = mobilenumber;
	}
}
