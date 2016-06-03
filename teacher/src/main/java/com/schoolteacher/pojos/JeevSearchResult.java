package com.schoolteacher.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class JeevSearchResult implements Serializable, Parcelable {
	private static final long serialVersionUID = 956800473923533715L;
	private List<JeevLinkedResult> LinkedResults;
	private int ProfileViewCount;
	private String UniquePublicID;
	private String ProfessionalProfileId;
	private String FacilityProfileId;
	private String Title;
	private String FirstName;
	private String LastName;
	private String ProfilePhoto;
	private int Id;
	private int ProfessionalId;
	private int FacilityId;
	private List<JeevContactInformation> ContactInformations;
	private List<String> Specialities;
	private List<String> ServicesOffered;
	private List<String> DealsAndOffers;
	private List<String> Expertise;
	private List<String> EducationalQualifications;
	private List<String> Experience;
	private String FullName;
	private int Rank;
	private float Rating;
	private String Fees;
	private String Gender;
	private boolean IsPublished;
	private boolean Verified;
	private boolean IsRecommended;
	private boolean IsPremium;
	private boolean IsVerified;
	private String Type;
	private String About;
	private double Distance;
	private int SearchScore;
	private int ExperienceYears;
	private boolean DiscountOffered;
	private boolean IsRegistered;
	private String ExperienceYearsText;
	private List<String> ServiceRequisitionTypes;
	private List<String> Conveniences;
	private double RelevanceScore;
	private String PremiumProfileURL;

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(ProfessionalProfileId);
		dest.writeString(FacilityProfileId);
		dest.writeString(Type);

	}

	private JeevSearchResult(Parcel in) {
		this.ProfessionalProfileId = in.readString();
		this.FacilityProfileId = in.readString();
		this.Type = in.readString();
	}

	public JeevSearchResult() {
		// TODO Auto-generated constructor stub
	}

	public static final Creator<JeevSearchResult> CREATOR = new Creator<JeevSearchResult>() {

		@Override
		public JeevSearchResult createFromParcel(Parcel source) {
			return new JeevSearchResult(source);
		}

		@Override
		public JeevSearchResult[] newArray(int size) {
			return new JeevSearchResult[size];
		}
	};

	public List<JeevLinkedResult> getLinkedResults() {
		return LinkedResults;
	}

	public void setLinkedResults(List<JeevLinkedResult> linkedResults) {
		LinkedResults = linkedResults;
	}

	public int getProfileViewCount() {
		return ProfileViewCount;
	}

	public void setProfileViewCount(int profileViewCount) {
		ProfileViewCount = profileViewCount;
	}

	public String getUniquePublicID() {
		return UniquePublicID;
	}

	public void setUniquePublicID(String uniquePublicID) {
		UniquePublicID = uniquePublicID;
	}

	public String getProfessionalProfileId() {
		return ProfessionalProfileId;
	}

	public void setProfessionalProfileId(String professionalProfileId) {
		ProfessionalProfileId = professionalProfileId;
	}

	public String getFacilityProfileId() {
		return FacilityProfileId;
	}

	public void setFacilityProfileId(String facilityProfileId) {
		FacilityProfileId = facilityProfileId;
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

	public String getProfilePhoto() {
		return ProfilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		ProfilePhoto = profilePhoto;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getProfessionalId() {
		return ProfessionalId;
	}

	public void setProfessionalId(int professionalId) {
		ProfessionalId = professionalId;
	}

	public int getFacilityId() {
		return FacilityId;
	}

	public void setFacilityId(int facilityId) {
		FacilityId = facilityId;
	}

	public List<JeevContactInformation> getContactInformations() {
		return ContactInformations;
	}

	public void setContactInformations(
			List<JeevContactInformation> contactInformations) {
		ContactInformations = contactInformations;
	}

	public String getFullName() {
		return FullName;
	}

	public List<String> getServicesOffered() {
		return ServicesOffered;
	}

	public void setServicesOffered(List<String> servicesOffered) {
		ServicesOffered = servicesOffered;
	}

	public List<String> getDealsAndOffers() {
		return DealsAndOffers;
	}

	public void setDealsAndOffers(List<String> dealsAndOffers) {
		DealsAndOffers = dealsAndOffers;
	}

	public List<String> getExpertise() {
		return Expertise;
	}

	public void setExpertise(List<String> expertise) {
		Expertise = expertise;
	}

	public List<String> getEducationalQualifications() {
		return EducationalQualifications;
	}

	public void setEducationalQualifications(
			List<String> educationalQualifications) {
		EducationalQualifications = educationalQualifications;
	}

	public List<String> getExperience() {
		return Experience;
	}

	public void setExperience(List<String> experience) {
		Experience = experience;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}

	public int getRank() {
		return Rank;
	}

	public void setRank(int rank) {
		Rank = rank;
	}

	public String getFees() {
		return Fees;
	}

	public void setFees(String fees) {
		Fees = fees;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public boolean isIsPublished() {
		return IsPublished;
	}

	public void setIsPublished(boolean isPublished) {
		IsPublished = isPublished;
	}

	public boolean isVerified() {
		return Verified;
	}

	public void setVerified(boolean verified) {
		Verified = verified;
	}

	public boolean isIsRecommended() {
		return IsRecommended;
	}

	public void setIsRecommended(boolean isRecommended) {
		IsRecommended = isRecommended;
	}

	public boolean isIsPremium() {
		return IsPremium;
	}

	public void setIsPremium(boolean isPremium) {
		IsPremium = isPremium;
	}

	public boolean isIsVerified() {
		return IsVerified;
	}

	public void setIsVerified(boolean isVerified) {
		IsVerified = isVerified;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getAbout() {
		return About;
	}

	public void setAbout(String about) {
		About = about;
	}

	public double getDistance() {
		return Distance;
	}

	public void setDistance(double distance) {
		Distance = distance;
	}

	public int getSearchScore() {
		return SearchScore;
	}

	public void setSearchScore(int searchScore) {
		SearchScore = searchScore;
	}

	public int getExperienceYears() {
		return ExperienceYears;
	}

	public void setExperienceYears(int experienceYears) {
		ExperienceYears = experienceYears;
	}

	public boolean isDiscountOffered() {
		return DiscountOffered;
	}

	public void setDiscountOffered(boolean discountOffered) {
		DiscountOffered = discountOffered;
	}

	public boolean isIsRegistered() {
		return IsRegistered;
	}

	public void setIsRegistered(boolean isRegistered) {
		IsRegistered = isRegistered;
	}

	public String getExperienceYearsText() {
		return ExperienceYearsText;
	}

	public void setExperienceYearsText(String experienceYearsText) {
		ExperienceYearsText = experienceYearsText;
	}

	public List<String> getServiceRequisitionTypes() {
		return ServiceRequisitionTypes;
	}

	public void setServiceRequisitionTypes(List<String> serviceRequisitionTypes) {
		ServiceRequisitionTypes = serviceRequisitionTypes;
	}

	public double getRelevanceScore() {
		return RelevanceScore;
	}

	public void setRelevanceScore(double relevanceScore) {
		RelevanceScore = relevanceScore;
	}

	public String getPremiumProfileURL() {
		return PremiumProfileURL;
	}

	public void setPremiumProfileURL(String premiumProfileURL) {
		PremiumProfileURL = premiumProfileURL;
	}

	public List<String> getSpecialities() {
		return Specialities;
	}

	public void setSpecialities(List<String> specialities) {
		Specialities = specialities;
	}

	public List<String> getConveniences() {
		return Conveniences;
	}

	public void setConveniences(List<String> conveniences) {
		Conveniences = conveniences;
	}

	public float getRating() {
		return Rating;
	}

	public void setRating(float rating) {
		Rating = rating;
	}

}
