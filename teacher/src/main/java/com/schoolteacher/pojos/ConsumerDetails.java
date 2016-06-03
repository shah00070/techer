package com.schoolteacher.pojos;

import com.schoolteacher.mylibrary.pojo.ConsultationDetails;
import com.schoolteacher.mylibrary.pojo.FacilityProfile;
import com.schoolteacher.mylibrary.pojo.ProfileSetting;

import java.io.Serializable;
import java.util.List;

public class ConsumerDetails implements Serializable {
	private int ProfileId;
	private int MemberId;
	private String FirstName;
	private String LastName;
	private String Weight;
	private String Height;
	private String HeightUnit;
	private String Title;
	private String FullName;
	private String Gender;
	private String Email;
	private String CellNumber;
	private String DateOfBirth;
	private String PhotoURL;
	private String ConsultationFees;
	private String IdentityMarks;
	private int StartYear;
	private String MedicalConditions;
	private String Allergies;
	private int BloodGroupType;
	private String ServicesOffered;
	private String Features;
	private String Degrees;
	private String Expertises;
	private String Specialities;
	private boolean IsFacilityProfile;
	private FacilityProfile FacilityProfile;

	/**
	 * @return the profileId
	 */
	public int getProfileId() {
		return ProfileId;
	}

	/**
	 * @param profileId
	 *            the profileId to set
	 */
	public void setProfileId(int profileId) {
		ProfileId = profileId;
	}

	/**
	 * @return the memberId
	 */
	public int getMemberId() {
		return MemberId;
	}

	/**
	 * @param memberId
	 *            the memberId to set
	 */
	public void setMemberId(int memberId) {
		MemberId = memberId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return FirstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return LastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		LastName = lastName;
	}

	/**
	 * @return the weight
	 */
	public String getWeight() {
		return Weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(String weight) {
		Weight = weight;
	}

	/**
	 * @return the height
	 */
	public String getHeight() {
		return Height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(String height) {
		Height = height;
	}

	/**
	 * @return the heightUnit
	 */
	public String getHeightUnit() {
		return HeightUnit;
	}

	/**
	 * @param heightUnit
	 *            the heightUnit to set
	 */
	public void setHeightUnit(String heightUnit) {
		HeightUnit = heightUnit;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return Title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		Title = title;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return FullName;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		FullName = fullName;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return Gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		Gender = gender;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return Email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		Email = email;
	}

	/**
	 * @return the cellNumber
	 */
	public String getCellNumber() {
		return CellNumber;
	}

	/**
	 * @param cellNumber
	 *            the cellNumber to set
	 */
	public void setCellNumber(String cellNumber) {
		CellNumber = cellNumber;
	}

	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return DateOfBirth;
	}

	/**
	 * @param dateOfBirth
	 *            the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}

	/**
	 * @return the photoURL
	 */
	public String getPhotoURL() {
		return PhotoURL;
	}

	/**
	 * @param photoURL
	 *            the photoURL to set
	 */
	public void setPhotoURL(String photoURL) {
		PhotoURL = photoURL;
	}

	/**
	 * @return the consultationFees
	 */
	public String getConsultationFees() {
		return ConsultationFees;
	}

	/**
	 * @param consultationFees
	 *            the consultationFees to set
	 */
	public void setConsultationFees(String consultationFees) {
		ConsultationFees = consultationFees;
	}

	/**
	 * @return the identityMarks
	 */
	public String getIdentityMarks() {
		return IdentityMarks;
	}

	/**
	 * @param identityMarks
	 *            the identityMarks to set
	 */
	public void setIdentityMarks(String identityMarks) {
		IdentityMarks = identityMarks;
	}

	/**
	 * @return the startYear
	 */
	public int getStartYear() {
		return StartYear;
	}

	/**
	 * @param startYear
	 *            the startYear to set
	 */
	public void setStartYear(int startYear) {
		StartYear = startYear;
	}

	/**
	 * @return the medicalConditions
	 */
	public String getMedicalConditions() {
		return MedicalConditions;
	}

	/**
	 * @param medicalConditions
	 *            the medicalConditions to set
	 */
	public void setMedicalConditions(String medicalConditions) {
		MedicalConditions = medicalConditions;
	}

	/**
	 * @return the allergies
	 */
	public String getAllergies() {
		return Allergies;
	}

	/**
	 * @param allergies
	 *            the allergies to set
	 */
	public void setAllergies(String allergies) {
		Allergies = allergies;
	}

	/**
	 * @return the servicesOffered
	 */
	public String getServicesOffered() {
		return ServicesOffered;
	}

	/**
	 * @param servicesOffered
	 *            the servicesOffered to set
	 */
	public void setServicesOffered(String servicesOffered) {
		ServicesOffered = servicesOffered;
	}

	/**
	 * @return the features
	 */
	public String getFeatures() {
		return Features;
	}

	/**
	 * @param features
	 *            the features to set
	 */
	public void setFeatures(String features) {
		Features = features;
	}

	/**
	 * @return the degrees
	 */
	public String getDegrees() {
		return Degrees;
	}

	/**
	 * @param degrees
	 *            the degrees to set
	 */
	public void setDegrees(String degrees) {
		Degrees = degrees;
	}

	/**
	 * @return the expertises
	 */
	public String getExpertises() {
		return Expertises;
	}

	/**
	 * @param expertises
	 *            the expertises to set
	 */
	public void setExpertises(String expertises) {
		Expertises = expertises;
	}

	/**
	 * @return the specialities
	 */
	public String getSpecialities() {
		return Specialities;
	}

	/**
	 * @param specialities
	 *            the specialities to set
	 */
	public void setSpecialities(String specialities) {
		Specialities = specialities;
	}

	/**
	 * @return the isFacilityProfile
	 */
	public boolean isIsFacilityProfile() {
		return IsFacilityProfile;
	}

	/**
	 * @param isFacilityProfile
	 *            the isFacilityProfile to set
	 */
	public void setIsFacilityProfile(boolean isFacilityProfile) {
		IsFacilityProfile = isFacilityProfile;
	}

	/**
	 * @return the facilityProfile
	 */
	public FacilityProfile getFacilityProfile() {
		return FacilityProfile;
	}

	/**
	 * @param facilityProfile
	 *            the facilityProfile to set
	 */
	public void setFacilityProfile(FacilityProfile facilityProfile) {
		FacilityProfile = facilityProfile;
	}

	/**
	 * @return the facilities
	 */
	public List<FacilityProfile> getFacilities() {
		return Facilities;
	}

	/**
	 * @param facilities
	 *            the facilities to set
	 */
	public void setFacilities(List<FacilityProfile> facilities) {
		Facilities = facilities;
	}

	/**
	 * @return the memberContactInformation
	 */
	public ConsumerAddress getMemberContactInformation() {
		return MemberContactInformation;
	}

	/**
	 * @param memberContactInformation
	 *            the memberContactInformation to set
	 */
	public void setMemberContactInformation(ConsumerAddress memberContactInformation) {
		MemberContactInformation = memberContactInformation;
	}

	/**
	 * @return the consultationDetails
	 */
	public List<ConsultationDetails> getConsultationDetails() {
		return ConsultationDetails;
	}

	/**
	 * @param consultationDetails
	 *            the consultationDetails to set
	 */
	public void setConsultationDetails(List<ConsultationDetails> consultationDetails) {
		ConsultationDetails = consultationDetails;
	}

	/**
	 * @return the profileSettingDetails
	 */
	public List<ProfileSetting> getProfileSettingDetails() {
		return ProfileSettingDetails;
	}

	/**
	 * @param profileSettingDetails
	 *            the profileSettingDetails to set
	 */
	public void setProfileSettingDetails(List<ProfileSetting> profileSettingDetails) {
		ProfileSettingDetails = profileSettingDetails;
	}

	/**
	 * @return the memberEmergencyDetails
	 */
	public List<MemberEmergencyDetail> getMemberEmergencyDetails() {
		return MemberEmergencyDetails;
	}

	/**
	 * @param memberEmergencyDetails
	 *            the memberEmergencyDetails to set
	 */
	public void setMemberEmergencyDetails(List<MemberEmergencyDetail> memberEmergencyDetails) {
		MemberEmergencyDetails = memberEmergencyDetails;
	}

	/**
	 * @return the bloodGroupType
	 */
	public int getBloodGroupType() {
		return BloodGroupType;
	}

	/**
	 * @param bloodGroupType
	 *            the bloodGroupType to set
	 */
	public void setBloodGroupType(int bloodGroupType) {
		BloodGroupType = bloodGroupType;
	}

	/**
	 * @return the memberInsuranceDetails
	 */
	public List<MemberInsuranceDetail> getMemberInsuranceDetails() {
		return MemberInsuranceDetails;
	}

	/**
	 * @param memberInsuranceDetails
	 *            the memberInsuranceDetails to set
	 */
	public void setMemberInsuranceDetails(List<MemberInsuranceDetail> memberInsuranceDetails) {
		MemberInsuranceDetails = memberInsuranceDetails;
	}

	private List<FacilityProfile> Facilities;
	private ConsumerAddress MemberContactInformation;
	private List<ConsultationDetails> ConsultationDetails;
	private List<ProfileSetting> ProfileSettingDetails;
	private List<MemberEmergencyDetail> MemberEmergencyDetails;
	private List<MemberInsuranceDetail> MemberInsuranceDetails;
}
