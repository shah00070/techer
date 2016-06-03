package com.schoolteacher.pojos;

import java.io.Serializable;
import java.util.List;

public class JeevFacilityProfile implements Serializable {
	private static final long serialVersionUID = -4743811455206512339L;
	private List<JeevFacilityService> Services;
	private List<JeevFacilityConvenience> Convenience;
	private String ProfilePhoto;
	private JeevFacilityContactInformation ContactInformation;
	private int AssociatedMemberId;
	private boolean IsRecommended;
	private int IsRecordVerifiedBySupport;
	private boolean IsProfileVerified;
	private boolean IsDiscountOffered;
	private boolean IsPremium;
	private boolean CanPublished;
	private int ClaimStatus;
	private boolean IsClaimed;
	private int Id;
	private String UniquePublicID;
	private String Name;
	private boolean IsDeleted;
	private String CreatedOnUTC;
	private int CreatedBy;
	private String UpdatedOnUTC;
	private int UpdatedBy;
	private boolean IsPrivate;
	private int YearFounded;
	private Stats Stats;
	private List<JeevServiceRequestType> ServiceRequestType;
	private List<DealAndOffer> DealAndOffers;
	private List<AwardsAndMention> AwardsAndMentions;
	private String About;

	// private object ProfileCategories ;

	// private object FacilityAvailabilities;
	// private object ProfessionalFacilityAvailabilities;
	// private object premiumProfileURL;
	// private object Rank;
	// private object Description;
	// private FacilityType FacilityType;
	// private object PanelMembersDetails;
	// private object OtherInformation;
	// private List<object> SocialMediaInformations;
	// private object AssociatedAccountId;
	// private List<object> PhotoGallary ;
	// private List<object> LogoGallary ;
	// private List<object> VideoSelfieGallary ;
	// private object Email ;
	// private object Phone ;
	// private object Phone2 ;
	// private object ServicesOffered ;
	// private object Features ;
	// private object StartYear ;
	// private object FacilityContactInformation;
	// private List<object> ConsultationModes;
	// private object AverageRating;
	// private object PFPrimaryEmailId;
	// private object PFFees;
	// private object PFPhone1;
	// private object PFPhone2;
	// private List<object> ProfileCategory;
	// private List<object> SearchCategory;
	public List<JeevFacilityService> getServices() {
		return Services;
	}

	public void setServices(List<JeevFacilityService> services) {
		Services = services;
	}

	public List<JeevFacilityConvenience> getConvenience() {
		return Convenience;
	}

	public void setConvenience(List<JeevFacilityConvenience> convenience) {
		Convenience = convenience;
	}

	public String getProfilePhoto() {
		return ProfilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		ProfilePhoto = profilePhoto;
	}

	public JeevFacilityContactInformation getContactInformation() {
		return ContactInformation;
	}

	public void setContactInformation(
			JeevFacilityContactInformation contactInformation) {
		ContactInformation = contactInformation;
	}

	public int getAssociatedMemberId() {
		return AssociatedMemberId;
	}

	public void setAssociatedMemberId(int associatedMemberId) {
		AssociatedMemberId = associatedMemberId;
	}

	public boolean isIsRecommended() {
		return IsRecommended;
	}

	public void setIsRecommended(boolean isRecommended) {
		IsRecommended = isRecommended;
	}

	public boolean isIsProfileVerified() {
		return IsProfileVerified;
	}

	public void setIsProfileVerified(boolean isProfileVerified) {
		IsProfileVerified = isProfileVerified;
	}

	public boolean isIsDiscountOffered() {
		return IsDiscountOffered;
	}

	public void setIsDiscountOffered(boolean isDiscountOffered) {
		IsDiscountOffered = isDiscountOffered;
	}

	public boolean isIsPremium() {
		return IsPremium;
	}

	public void setIsPremium(boolean isPremium) {
		IsPremium = isPremium;
	}

	public boolean isCanPublished() {
		return CanPublished;
	}

	public void setCanPublished(boolean canPublished) {
		CanPublished = canPublished;
	}

	public int getClaimStatus() {
		return ClaimStatus;
	}

	public void setClaimStatus(int claimStatus) {
		ClaimStatus = claimStatus;
	}

	public boolean isIsClaimed() {
		return IsClaimed;
	}

	public void setIsClaimed(boolean isClaimed) {
		IsClaimed = isClaimed;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getUniquePublicID() {
		return UniquePublicID;
	}

	public void setUniquePublicID(String uniquePublicID) {
		UniquePublicID = uniquePublicID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public boolean isIsDeleted() {
		return IsDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		IsDeleted = isDeleted;
	}

	public String getCreatedOnUTC() {
		return CreatedOnUTC;
	}

	public void setCreatedOnUTC(String createdOnUTC) {
		CreatedOnUTC = createdOnUTC;
	}

	public int getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(int createdBy) {
		CreatedBy = createdBy;
	}

	public String getUpdatedOnUTC() {
		return UpdatedOnUTC;
	}

	public void setUpdatedOnUTC(String updatedOnUTC) {
		UpdatedOnUTC = updatedOnUTC;
	}

	public int getUpdatedBy() {
		return UpdatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		UpdatedBy = updatedBy;
	}

	public boolean isIsPrivate() {
		return IsPrivate;
	}

	public void setIsPrivate(boolean isPrivate) {
		IsPrivate = isPrivate;
	}

	public int getYearFounded() {
		return YearFounded;
	}

	public void setYearFounded(int yearFounded) {
		YearFounded = yearFounded;
	}

	public List<JeevServiceRequestType> getServiceRequestType() {
		return ServiceRequestType;
	}

	public void setServiceRequestType(
			List<JeevServiceRequestType> serviceRequestType) {
		ServiceRequestType = serviceRequestType;
	}

	public List<DealAndOffer> getDealAndOffers() {
		return DealAndOffers;
	}

	public void setDealAndOffers(List<DealAndOffer> dealAndOffers) {
		DealAndOffers = dealAndOffers;
	}

	public List<AwardsAndMention> getAwardsAndMentions() {
		return AwardsAndMentions;
	}

	public void setAwardsAndMentions(List<AwardsAndMention> awardsAndMentions) {
		AwardsAndMentions = awardsAndMentions;
	}

	public String getAbout() {
		return About;
	}

	public void setAbout(String about) {
		About = about;
	}

	public Stats getStats() {
		return Stats;
	}

	public void setStats(Stats stats) {
		Stats = stats;
	}

	public int getIsRecordVerifiedBySupport() {
		return IsRecordVerifiedBySupport;
	}

	public void setIsRecordVerifiedBySupport(int isRecordVerifiedBySupport) {
		IsRecordVerifiedBySupport = isRecordVerifiedBySupport;
	}
}
