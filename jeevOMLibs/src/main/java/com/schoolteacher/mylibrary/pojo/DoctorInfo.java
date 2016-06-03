package com.schoolteacher.mylibrary.pojo;

import java.io.Serializable;
import java.util.List;

public class DoctorInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<DoctorContactInformation> ContactInformations;
	private List<SocialMediaInformation> SocialMediaInformations;
	private List<PhotoGallary> PhotoGallary;
	private int MemberId;
	private String About;
	private String firstName;
	private String lastName;
	private String ProfileId;
	private PremiumProfileURL premiumProfileURL;
	private List<DoctorServices> Services;
	private List<DoctorExpertise> Expertise;
	private String PracticeSince;
	private int ClaimStatus;
	private String OtherInformation;
	private String Availabilities;
	private List<AwardsAndMention> AwardsAndMentions;
	private List<DoctorEducationDetails> EducationDetails;
	private List<DoctorMembership> Memberships;
	private List<DoctorCertification> Certifications;
	private List<DoctorLanguage> Languages;
	private String Fees;
	private List<DoctorExperience> Experiences;
	private String Specialities;
	private List<DoctorSpecialityList> SpecialityList;
	private String Facilities;
	private String Degrees;
	private List<DoctorRegistrationDetails> RegistrationDetails;
	private int UserSubTypeId;
	private String Rank;
	private String AssociatedMemberId;
	private String AssociatedAccountId;
	private int Id;
	private String UniquePublicID;
	private String Title;
	private boolean IsPremium;
	private String FullName;
	private String Email;
	private String Experience;
	private String Phone;
	private String Photo;
	private String DateOfBirth;
	private String BloodGroup;
	private int DoctorRank;
	private String Gender;
	private boolean IsRecordVerifiedBySupport;
	private boolean CanPublished;
	private boolean Verified;
	private boolean IsClaimed;
	private boolean IsRecommended;
	private String ProfileClass;
	private String ProfileCategories;
	private List<DoctorDealAndOffer> DealAndOffer;
	private List<ConsultationMode> ConsultationModes;
	private Stats Stats;
	/**
	 * @return the contactInformations
	 */
	public List<DoctorContactInformation> getContactInformations() {
		return ContactInformations;
	}

	/**
	 * @param contactInformations
	 *            the contactInformations to set
	 */
	public void setContactInformations(List<DoctorContactInformation> contactInformations) {
		ContactInformations = contactInformations;
	}

	/**
	 * @return the socialMediaInformations
	 */
	public List<SocialMediaInformation> getSocialMediaInformations() {
		return SocialMediaInformations;
	}

	/**
	 * @param socialMediaInformations
	 *            the socialMediaInformations to set
	 */
	public void setSocialMediaInformations(List<SocialMediaInformation> socialMediaInformations) {
		SocialMediaInformations = socialMediaInformations;
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
	 * @return the about
	 */
	public String getAbout() {
		return About;
	}

	/**
	 * @param about
	 *            the about to set
	 */
	public void setAbout(String about) {
		About = about;
	}

	/**
	 * @return the profileId
	 */
	public String getProfileId() {
		return ProfileId;
	}

	/**
	 * @param profileId
	 *            the profileId to set
	 */
	public void setProfileId(String profileId) {
		ProfileId = profileId;
	}

	/**
	 * @return the services
	 */
	public List<DoctorServices> getServices() {
		return Services;
	}

	/**
	 * @param services
	 *            the services to set
	 */
	public void setServices(List<DoctorServices> services) {
		Services = services;
	}

	/**
	 * @return the expertise
	 */
	public List<DoctorExpertise> getExpertise() {
		return Expertise;
	}

	/**
	 * @param expertise
	 *            the expertise to set
	 */
	public void setExpertise(List<DoctorExpertise> expertise) {
		Expertise = expertise;
	}

	/**
	 * @return the practiceSince
	 */
	public String getPracticeSince() {
		return PracticeSince;
	}

	/**
	 * @param practiceSince
	 *            the practiceSince to set
	 */
	public void setPracticeSince(String practiceSince) {
		PracticeSince = practiceSince;
	}

	/**
	 * @return the otherInformation
	 */
	public String getOtherInformation() {
		return OtherInformation;
	}

	/**
	 * @param otherInformation
	 *            the otherInformation to set
	 */
	public void setOtherInformation(String otherInformation) {
		OtherInformation = otherInformation;
	}

	/**
	 * @return the availabilities
	 */
	public String getAvailabilities() {
		return Availabilities;
	}

	/**
	 * @param availabilities
	 *            the availabilities to set
	 */
	public void setAvailabilities(String availabilities) {
		Availabilities = availabilities;
	}

	/**
	 * @return the awardsAndMentions
	 */
	public List<AwardsAndMention> getAwardsAndMentions() {
		return AwardsAndMentions;
	}

	/**
	 * @param awardsAndMentions
	 *            the awardsAndMentions to set
	 */
	public void setAwardsAndMentions(List<AwardsAndMention> awardsAndMentions) {
		AwardsAndMentions = awardsAndMentions;
	}

	/**
	 * @return the educationDetails
	 */
	public List<DoctorEducationDetails> getEducationDetails() {
		return EducationDetails;
	}

	/**
	 * @param educationDetails
	 *            the educationDetails to set
	 */
	public void setEducationDetails(List<DoctorEducationDetails> educationDetails) {
		EducationDetails = educationDetails;
	}

	/**
	 * @return the fees
	 */
	public String getFees() {
		return Fees;
	}

	/**
	 * @param fees
	 *            the fees to set
	 */
	public void setFees(String fees) {
		Fees = fees;
	}

	/**
	 * @return the experiences
	 */
	public List<DoctorExperience> getExperiences() {
		return Experiences;
	}

	/**
	 * @param experiences
	 *            the experiences to set
	 */
	public void setExperiences(List<DoctorExperience> experiences) {
		Experiences = experiences;
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
	 * @return the specialityList
	 */
	public List<DoctorSpecialityList> getSpecialityList() {
		return SpecialityList;
	}

	/**
	 * @param specialityList
	 *            the specialityList to set
	 */
	public void setSpecialityList(List<DoctorSpecialityList> specialityList) {
		SpecialityList = specialityList;
	}

	/**
	 * @return the facilities
	 */
	public String getFacilities() {
		return Facilities;
	}

	/**
	 * @param facilities
	 *            the facilities to set
	 */
	public void setFacilities(String facilities) {
		Facilities = facilities;
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
	 * @return the registrationDetails
	 */
	public List<DoctorRegistrationDetails> getRegistrationDetails() {
		return RegistrationDetails;
	}

	/**
	 * @param registrationDetails
	 *            the registrationDetails to set
	 */
	public void setRegistrationDetails(List<DoctorRegistrationDetails> registrationDetails) {
		RegistrationDetails = registrationDetails;
	}

	/**
	 * @return the userSubTypeId
	 */
	public int getUserSubTypeId() {
		return UserSubTypeId;
	}

	/**
	 * @param userSubTypeId
	 *            the userSubTypeId to set
	 */
	public void setUserSubTypeId(int userSubTypeId) {
		UserSubTypeId = userSubTypeId;
	}

	/**
	 * @return the rank
	 */
	public String getRank() {
		return Rank;
	}

	/**
	 * @param rank
	 *            the rank to set
	 */
	public void setRank(String rank) {
		Rank = rank;
	}

	/**
	 * @return the associatedMemberId
	 */
	public String getAssociatedMemberId() {
		return AssociatedMemberId;
	}

	/**
	 * @param associatedMemberId
	 *            the associatedMemberId to set
	 */
	public void setAssociatedMemberId(String associatedMemberId) {
		AssociatedMemberId = associatedMemberId;
	}

	/**
	 * @return the associatedAccountId
	 */
	public String getAssociatedAccountId() {
		return AssociatedAccountId;
	}

	/**
	 * @param associatedAccountId
	 *            the associatedAccountId to set
	 */
	public void setAssociatedAccountId(String associatedAccountId) {
		AssociatedAccountId = associatedAccountId;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return Id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		Id = id;
	}

	/**
	 * @return the uniquePublicID
	 */
	public String getUniquePublicID() {
		return UniquePublicID;
	}

	/**
	 * @param uniquePublicID
	 *            the uniquePublicID to set
	 */
	public void setUniquePublicID(String uniquePublicID) {
		UniquePublicID = uniquePublicID;
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
	 * @return the isPremium
	 */
	public boolean isIsPremium() {
		return IsPremium;
	}

	/**
	 * @param isPremium
	 *            the isPremium to set
	 */
	public void setIsPremium(boolean isPremium) {
		IsPremium = isPremium;
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
	 * @return the experience
	 */
	public String getExperience() {
		return Experience;
	}

	/**
	 * @param experience
	 *            the experience to set
	 */
	public void setExperience(String experience) {
		Experience = experience;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return Phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		Phone = phone;
	}

	/**
	 * @return the photo
	 */
	public String getPhoto() {
		return Photo;
	}

	/**
	 * @param photo
	 *            the photo to set
	 */
	public void setPhoto(String photo) {
		Photo = photo;
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
	 * @return the bloodGroup
	 */
	public String getBloodGroup() {
		return BloodGroup;
	}

	/**
	 * @param bloodGroup
	 *            the bloodGroup to set
	 */
	public void setBloodGroup(String bloodGroup) {
		BloodGroup = bloodGroup;
	}

	/**
	 * @return the doctorRank
	 */
	public int getDoctorRank() {
		return DoctorRank;
	}

	/**
	 * @param doctorRank
	 *            the doctorRank to set
	 */
	public void setDoctorRank(int doctorRank) {
		DoctorRank = doctorRank;
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
	 * @return the isRecordVerifiedBySupport
	 */
	public boolean isIsRecordVerifiedBySupport() {
		return IsRecordVerifiedBySupport;
	}

	/**
	 * @param isRecordVerifiedBySupport
	 *            the isRecordVerifiedBySupport to set
	 */
	public void setIsRecordVerifiedBySupport(boolean isRecordVerifiedBySupport) {
		IsRecordVerifiedBySupport = isRecordVerifiedBySupport;
	}

	/**
	 * @return the canPublished
	 */
	public boolean isCanPublished() {
		return CanPublished;
	}

	/**
	 * @param canPublished
	 *            the canPublished to set
	 */
	public void setCanPublished(boolean canPublished) {
		CanPublished = canPublished;
	}

	/**
	 * @return the verified
	 */
	public boolean isVerified() {
		return Verified;
	}

	/**
	 * @param verified
	 *            the verified to set
	 */
	public void setVerified(boolean verified) {
		Verified = verified;
	}

	/**
	 * @return the isClaimed
	 */
	public boolean isIsClaimed() {
		return IsClaimed;
	}

	/**
	 * @param isClaimed
	 *            the isClaimed to set
	 */
	public void setIsClaimed(boolean isClaimed) {
		IsClaimed = isClaimed;
	}

	/**
	 * @return the isRecommended
	 */
	public boolean isIsRecommended() {
		return IsRecommended;
	}

	/**
	 * @param isRecommended
	 *            the isRecommended to set
	 */
	public void setIsRecommended(boolean isRecommended) {
		IsRecommended = isRecommended;
	}

	/**
	 * @return the profileClass
	 */
	public String getProfileClass() {
		return ProfileClass;
	}

	/**
	 * @param profileClass
	 *            the profileClass to set
	 */
	public void setProfileClass(String profileClass) {
		ProfileClass = profileClass;
	}

	/**
	 * @return the profileCategories
	 */
	public String getProfileCategories() {
		return ProfileCategories;
	}

	/**
	 * @param profileCategories
	 *            the profileCategories to set
	 */
	public void setProfileCategories(String profileCategories) {
		ProfileCategories = profileCategories;
	}

	public List<DoctorMembership> getMemberships() {
		return Memberships;
	}

	public void setMemberships(List<DoctorMembership> memberships) {
		Memberships = memberships;
	}

	public List<DoctorCertification> getCertifications() {
		return Certifications;
	}

	public void setCertifications(List<DoctorCertification> certifications) {
		Certifications = certifications;
	}

	public List<DoctorLanguage> getLanguages() {
		return Languages;
	}

	public void setLanguages(List<DoctorLanguage> languages) {
		Languages = languages;
	}

	public List<DoctorDealAndOffer> getDealAndOffer() {
		return DealAndOffer;
	}

	public void setDealAndOffer(List<DoctorDealAndOffer> dealAndOffer) {
		DealAndOffer = dealAndOffer;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the claimStatus
	 */
	public int getClaimStatus() {
		return ClaimStatus;
	}

	/**
	 * @param claimStatus
	 *            the claimStatus to set
	 */
	public void setClaimStatus(int claimStatus) {
		ClaimStatus = claimStatus;
	}

	/**
	 * @return the premiumProfileURL
	 */
	public PremiumProfileURL getPremiumProfileURL() {
		return premiumProfileURL;
	}

	/**
	 * @param premiumProfileURL
	 *            the premiumProfileURL to set
	 */
	public void setPremiumProfileURL(PremiumProfileURL premiumProfileURL) {
		this.premiumProfileURL = premiumProfileURL;
	}

	/**
	 * @return the consultationModes
	 */
	public List<ConsultationMode> getConsultationModes() {
		return ConsultationModes;
	}

	/**
	 * @param consultationModes
	 *            the consultationModes to set
	 */
	public void setConsultationModes(List<ConsultationMode> consultationModes) {
		ConsultationModes = consultationModes;
	}

	/**
	 * @return the photoGallary
	 */
	public List<PhotoGallary> getPhotoGallary() {
		return PhotoGallary;
	}

	/**
	 * @param photoGallary the photoGallary to set
	 */
	public void setPhotoGallary(List<PhotoGallary> photoGallary) {
		PhotoGallary = photoGallary;
	}

	/**
	 * @return the stats
	 */
	public Stats getStats() {
		return Stats;
	}

	/**
	 * @param stats the stats to set
	 */
	public void setStats(Stats stats) {
		Stats = stats;
	}

}
