package com.schoolteacher.mylibrary.pojo;

import java.io.Serializable;
import java.util.List;

public class FacilityProfile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ProfileCategories;
	private List<FacilityService> Services;
	private List<FacilityDealAndOffer> DealAndOffers;
	private List<FacilityConvenience> Convenience;
	private String FacilityAvailabilities;
	private String ProfessionalFacilityAvailabilities;
	private String Rank;
	private String ProfilePhoto;
	private String Description;
	private FacilityType FacilityType;
	private List<AwardsAndMention> AwardsAndMentions;
	private String PanelMembersDetails;
	private String OtherInformation;
	private ContactInformation ContactInformation;
	private List<SocialMediaInformation> SocialMediaInformations;
	private int AssociatedMemberId;
	private String AssociatedAccountId;
	private boolean IsRecommended;
	private boolean IsRecordVerifiedBySupport;
	private boolean IsPremium;
	private boolean CanPublished;
	// private List<PhotoGallary> PhotoGallary;
	private String Email;
	private String Phone;
	private String ServicesOffered;
	private String Features;
	private String StartYear;
	private String About;
	private String FacilityContactInformation;
	private int Id;
	private String UniquePublicID;
	private String Name;
	private boolean IsDeleted;
	private String CreatedOnUTC;
	private int CreatedBy;
	private String UpdatedOnUTC;
	private int UpdatedBy;
	private String PFPrimaryEmailId;
	private String PFFees;
	private String PFPhone1;
	private String PFPhone2;
	private List<ConsultationMode> ConsultationModes;

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

	/**
	 * @return the facilityAvailabilities
	 */
	public String getFacilityAvailabilities() {
		return FacilityAvailabilities;
	}

	/**
	 * @param facilityAvailabilities
	 *            the facilityAvailabilities to set
	 */
	public void setFacilityAvailabilities(String facilityAvailabilities) {
		FacilityAvailabilities = facilityAvailabilities;
	}

	/**
	 * @return the professionalFacilityAvailabilities
	 */
	public String getProfessionalFacilityAvailabilities() {
		return ProfessionalFacilityAvailabilities;
	}

	/**
	 * @param professionalFacilityAvailabilities
	 *            the professionalFacilityAvailabilities to set
	 */
	public void setProfessionalFacilityAvailabilities(String professionalFacilityAvailabilities) {
		ProfessionalFacilityAvailabilities = professionalFacilityAvailabilities;
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
	 * @return the profilePhoto
	 */
	public String getProfilePhoto() {
		return ProfilePhoto;
	}

	/**
	 * @param profilePhoto
	 *            the profilePhoto to set
	 */
	public void setProfilePhoto(String profilePhoto) {
		ProfilePhoto = profilePhoto;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return Description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		Description = description;
	}

	/**
	 * @return the facilityType
	 */
	public FacilityType getFacilityType() {
		return FacilityType;
	}

	/**
	 * @param facilityType
	 *            the facilityType to set
	 */
	public void setFacilityType(FacilityType facilityType) {
		FacilityType = facilityType;
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
	 * @return the panelMembersDetails
	 */
	public String getPanelMembersDetails() {
		return PanelMembersDetails;
	}

	/**
	 * @param panelMembersDetails
	 *            the panelMembersDetails to set
	 */
	public void setPanelMembersDetails(String panelMembersDetails) {
		PanelMembersDetails = panelMembersDetails;
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
	 * @return the contactInformation
	 */
	public ContactInformation getContactInformation() {
		return ContactInformation;
	}

	/**
	 * @param contactInformation
	 *            the contactInformation to set
	 */
	public void setContactInformation(ContactInformation contactInformation) {
		ContactInformation = contactInformation;
	}

	/**
	 * @return the associatedMemberId
	 */
	public int getAssociatedMemberId() {
		return AssociatedMemberId;
	}

	public List<FacilityService> getServices() {
		return Services;
	}

	public void setServices(List<FacilityService> services) {
		Services = services;
	}

	public List<FacilityDealAndOffer> getDealAndOffers() {
		return DealAndOffers;
	}

	public void setDealAndOffers(List<FacilityDealAndOffer> dealAndOffers) {
		DealAndOffers = dealAndOffers;
	}

	public List<FacilityConvenience> getConvenience() {
		return Convenience;
	}

	public void setConvenience(List<FacilityConvenience> convenience) {
		Convenience = convenience;
	}

	public List<SocialMediaInformation> getSocialMediaInformations() {
		return SocialMediaInformations;
	}

	public void setSocialMediaInformations(List<SocialMediaInformation> socialMediaInformations) {
		SocialMediaInformations = socialMediaInformations;
	}

	/**
	 * @param associatedMemberId
	 *            the associatedMemberId to set
	 */
	public void setAssociatedMemberId(int associatedMemberId) {
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
	 * @return the startYear
	 */
	public String getStartYear() {
		return StartYear;
	}

	/**
	 * @param startYear
	 *            the startYear to set
	 */
	public void setStartYear(String startYear) {
		StartYear = startYear;
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
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * @return the isDeleted
	 */
	public boolean isIsDeleted() {
		return IsDeleted;
	}

	/**
	 * @param isDeleted
	 *            the isDeleted to set
	 */
	public void setIsDeleted(boolean isDeleted) {
		IsDeleted = isDeleted;
	}

	/**
	 * @return the createdOnUTC
	 */
	public String getCreatedOnUTC() {
		return CreatedOnUTC;
	}

	/**
	 * @param createdOnUTC
	 *            the createdOnUTC to set
	 */
	public void setCreatedOnUTC(String createdOnUTC) {
		CreatedOnUTC = createdOnUTC;
	}

	/**
	 * @return the createdBy
	 */
	public int getCreatedBy() {
		return CreatedBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(int createdBy) {
		CreatedBy = createdBy;
	}

	/**
	 * @return the updatedOnUTC
	 */
	public String getUpdatedOnUTC() {
		return UpdatedOnUTC;
	}

	/**
	 * @param updatedOnUTC
	 *            the updatedOnUTC to set
	 */
	public void setUpdatedOnUTC(String updatedOnUTC) {
		UpdatedOnUTC = updatedOnUTC;
	}

	/**
	 * @return the updatedBy
	 */
	public int getUpdatedBy() {
		return UpdatedBy;
	}

	/**
	 * @param updatedBy
	 *            the updatedBy to set
	 */
	public void setUpdatedBy(int updatedBy) {
		UpdatedBy = updatedBy;
	}

	/**
	 * @return the pFPrimaryEmailId
	 */
	public String getPFPrimaryEmailId() {
		return PFPrimaryEmailId;
	}

	/**
	 * @param pFPrimaryEmailId
	 *            the pFPrimaryEmailId to set
	 */
	public void setPFPrimaryEmailId(String pFPrimaryEmailId) {
		PFPrimaryEmailId = pFPrimaryEmailId;
	}

	/**
	 * @return the pFFees
	 */
	public String getPFFees() {
		return PFFees;
	}

	/**
	 * @param pFFees
	 *            the pFFees to set
	 */
	public void setPFFees(String pFFees) {
		PFFees = pFFees;
	}

	/**
	 * @return the pFPhone1
	 */
	public String getPFPhone1() {
		return PFPhone1;
	}

	/**
	 * @param pFPhone1
	 *            the pFPhone1 to set
	 */
	public void setPFPhone1(String pFPhone1) {
		PFPhone1 = pFPhone1;
	}

	/**
	 * @return the pFPhone2
	 */
	public String getPFPhone2() {
		return PFPhone2;
	}

	/**
	 * @param pFPhone2
	 *            the pFPhone2 to set
	 */
	public void setPFPhone2(String pFPhone2) {
		PFPhone2 = pFPhone2;
	}

	/**
	 * @return the isPrivate
	 */
	public boolean isIsPrivate() {
		return IsPrivate;
	}

	/**
	 * @param isPrivate
	 *            the isPrivate to set
	 */
	public void setIsPrivate(boolean isPrivate) {
		IsPrivate = isPrivate;
	}

	/**
	 * @return the yearFounded
	 */
	public int getYearFounded() {
		return YearFounded;
	}

	/**
	 * @param yearFounded
	 *            the yearFounded to set
	 */
	public void setYearFounded(int yearFounded) {
		YearFounded = yearFounded;
	}

	/**
	 * @return the facilityContactInformation
	 */
	public String getFacilityContactInformation() {
		return FacilityContactInformation;
	}

	/**
	 * @param facilityContactInformation
	 *            the facilityContactInformation to set
	 */
	public void setFacilityContactInformation(String facilityContactInformation) {
		FacilityContactInformation = facilityContactInformation;
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

	private boolean IsPrivate;
	private int YearFounded;
	private Stats Stats;
}
