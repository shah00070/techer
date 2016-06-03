package com.schoolteacher.pojos;

import java.io.Serializable;
import java.util.List;

public class JeevProfileInfo implements Serializable {
	private static final long serialVersionUID = 1595837870035580978L;
	private List<ProfileContactInformation> ContactInformations;
	// private List<object> SocialMediaInformations;
	// private List<object> PhotoGallary;
	private List<ProfilePicsGallary> ProfilePicsGallary;
	// private List<object> VideoSelfieGallary;
	private int MemberId;
	private String About;
	private String firstName;
	private String lastName;
	private String ProfileId;
	private PremiumProfileURL premiumProfileURL;
	// private List<object> Services;
	private List<ProfileExpertise> Expertise;
	private int PracticeSince;
	private int ClaimStatus;

	private List<ProfileEducationDetail> EducationDetails;
	private List<ProfileMembership> Memberships;
	private List<ProfileLanguage> Languages;
	private String Fees;
	private List<ProfileExperience> Experiences;
	// private object Specialities;
	private List<ProfileSpecialityList> SpecialityList;
	// private object Facilities;
	// private object Degrees;
	// private List<object> RegistrationDetails;
	private int UserSubTypeId;
	// private object Rank;
	// private object AssociatedMemberId;
	// private object AssociatedAccountId;
	private int Id;
	// private object UniqueprivateID;
	private String Title;
	private boolean IsPremium;
	private boolean IsDiscountOffered;
	private String FullName;
	private String Email;
	private String Experience;
	private String Phone;
	private String Photo;
	private String DateOfBirth;
	// private object BloodGroup;
	private int DoctorRank;
	private String Gender;
	private int IsRecordVerifiedBySupport;
	private boolean IsProfileVerified;
	private boolean CanPublished;
	private boolean Verified;
	private boolean IsClaimed;
	private boolean IsRecommended;
	private List<ProfileService> Services;
	// private object ProfileClass;
	// private object ProfileCategories;
	private List<ProfileDealAndOffer> DealAndOffer;
	private List<ProfileConsultationMode> ConsultationModes;

	public List<ProfileContactInformation> getContactInformations() {
		return ContactInformations;
	}

	public void setContactInformations(
			List<ProfileContactInformation> contactInformations) {
		ContactInformations = contactInformations;
	}

	public List<ProfilePicsGallary> getProfilePicsGallary() {
		return ProfilePicsGallary;
	}

	public void setProfilePicsGallary(
			List<ProfilePicsGallary> profilePicsGallary) {
		ProfilePicsGallary = profilePicsGallary;
	}

	public int getMemberId() {
		return MemberId;
	}

	public void setMemberId(int memberId) {
		MemberId = memberId;
	}

	public String getAbout() {
		return About;
	}

	public void setAbout(String about) {
		About = about;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getProfileId() {
		return ProfileId;
	}

	public void setProfileId(String profileId) {
		ProfileId = profileId;
	}

	public PremiumProfileURL getPremiumProfileURL() {
		return premiumProfileURL;
	}

	public void setPremiumProfileURL(PremiumProfileURL premiumProfileURL) {
		this.premiumProfileURL = premiumProfileURL;
	}

	public List<ProfileExpertise> getExpertise() {
		return Expertise;
	}

	public void setExpertise(List<ProfileExpertise> expertise) {
		Expertise = expertise;
	}

	public int getPracticeSince() {
		return PracticeSince;
	}

	public void setPracticeSince(int practiceSince) {
		PracticeSince = practiceSince;
	}

	public int getClaimStatus() {
		return ClaimStatus;
	}

	public void setClaimStatus(int claimStatus) {
		ClaimStatus = claimStatus;
	}

	public List<ProfileEducationDetail> getEducationDetails() {
		return EducationDetails;
	}

	public void setEducationDetails(
			List<ProfileEducationDetail> educationDetails) {
		EducationDetails = educationDetails;
	}

	public List<ProfileLanguage> getLanguages() {
		return Languages;
	}

	public void setLanguages(List<ProfileLanguage> languages) {
		Languages = languages;
	}

	public String getFees() {
		return Fees;
	}

	public void setFees(String fees) {
		Fees = fees;
	}

	public List<ProfileExperience> getExperiences() {
		return Experiences;
	}

	public void setExperiences(List<ProfileExperience> experiences) {
		Experiences = experiences;
	}

	public List<ProfileSpecialityList> getSpecialityList() {
		return SpecialityList;
	}

	public void setSpecialityList(List<ProfileSpecialityList> specialityList) {
		SpecialityList = specialityList;
	}

	public int getUserSubTypeId() {
		return UserSubTypeId;
	}

	public void setUserSubTypeId(int userSubTypeId) {
		UserSubTypeId = userSubTypeId;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public boolean isIsPremium() {
		return IsPremium;
	}

	public void setIsPremium(boolean isPremium) {
		IsPremium = isPremium;
	}

	public boolean isIsDiscountOffered() {
		return IsDiscountOffered;
	}

	public void setIsDiscountOffered(boolean isDiscountOffered) {
		IsDiscountOffered = isDiscountOffered;
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

	public String getExperience() {
		return Experience;
	}

	public void setExperience(String experience) {
		Experience = experience;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getPhoto() {
		return Photo;
	}

	public void setPhoto(String photo) {
		Photo = photo;
	}

	public String getDateOfBirth() {
		return DateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}

	public int getDoctorRank() {
		return DoctorRank;
	}

	public void setDoctorRank(int doctorRank) {
		DoctorRank = doctorRank;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public boolean isIsProfileVerified() {
		return IsProfileVerified;
	}

	public void setIsProfileVerified(boolean isProfileVerified) {
		IsProfileVerified = isProfileVerified;
	}

	public boolean isCanPublished() {
		return CanPublished;
	}

	public void setCanPublished(boolean canPublished) {
		CanPublished = canPublished;
	}

	public boolean isVerified() {
		return Verified;
	}

	public void setVerified(boolean verified) {
		Verified = verified;
	}

	public boolean isIsClaimed() {
		return IsClaimed;
	}

	public void setIsClaimed(boolean isClaimed) {
		IsClaimed = isClaimed;
	}

	public boolean isIsRecommended() {
		return IsRecommended;
	}

	public void setIsRecommended(boolean isRecommended) {
		IsRecommended = isRecommended;
	}

	public List<ProfileConsultationMode> getConsultationModes() {
		return ConsultationModes;
	}

	public void setConsultationModes(
			List<ProfileConsultationMode> consultationModes) {
		ConsultationModes = consultationModes;
	}

	public List<ProfileCategory> getProfileCategory() {
		return ProfileCategory;
	}

	public void setProfileCategory(List<ProfileCategory> profileCategory) {
		ProfileCategory = profileCategory;
	}

	public List<ProfileSearchCategory> getSearchCategory() {
		return SearchCategory;
	}

	public void setSearchCategory(List<ProfileSearchCategory> searchCategory) {
		SearchCategory = searchCategory;
	}

	public List<ProfileServiceRequestType> getServiceRequestType() {
		return ServiceRequestType;
	}

	public void setServiceRequestType(
			List<ProfileServiceRequestType> serviceRequestType) {
		ServiceRequestType = serviceRequestType;
	}

	public Stats getStats() {
		return Stats;
	}

	public void setStats(Stats stats) {
		Stats = stats;
	}

	public List<ProfileService> getServices() {
		return Services;
	}

	public void setServices(List<ProfileService> services) {
		Services = services;
	}

	public List<ProfileDealAndOffer> getDealAndOffer() {
		return DealAndOffer;
	}

	public void setDealAndOffer(List<ProfileDealAndOffer> dealAndOffer) {
		DealAndOffer = dealAndOffer;
	}

	public List<ProfileMembership> getMemberships() {
		return Memberships;
	}

	public void setMemberships(List<ProfileMembership> memberships) {
		Memberships = memberships;
	}

	public int getIsRecordVerifiedBySupport() {
		return IsRecordVerifiedBySupport;
	}

	public void setIsRecordVerifiedBySupport(int isRecordVerifiedBySupport) {
		IsRecordVerifiedBySupport = isRecordVerifiedBySupport;
	}

	private Stats Stats;
	private List<ProfileCategory> ProfileCategory;
	private List<ProfileSearchCategory> SearchCategory;
	private List<ProfileServiceRequestType> ServiceRequestType;
}
