package com.schoolteacher.pojos;

import com.schoolteacher.mylibrary.pojo.ContactInformation;
import com.schoolteacher.mylibrary.util.CommonCode;

import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable {

	private static final long serialVersionUID = 1L;
	private String UniqueProfileId;
	private boolean IsDeleted;
	private String Title;
	private String FirstName;
	private String LastName;
	private String ProfilePhoto;
	private int CreatedBy;
	private int UpdatedBy;
	private int Id;
	private int ProfessionalId;
	private int FacilityId;
	private String CreatedOnUTC;
	private String UpdatedOnUTC;
	private List<ContactInformation> ContactInformations;
	private List<String> Specialities;
	private List<String> ServicesOffered;
	private List<String> DealsAndOffers;
	private List<String> Expertise;
	private List<String> EducationalQualifications;
	private List<String> Experience;
	private String FullName;
	private List<String> ConsultationModes;
	private String Email;
	private String CellNumber;
	private int Rank;
	private String FacilityProfileId;
	private String ProfessionalProfileId;

	/**
	 * @return the professionalProfileId
	 */
	public String getProfessionalProfileId() {
		return ProfessionalProfileId;
	}

	/**
	 * @param professionalProfileId
	 *            the professionalProfileId to set
	 */
	public void setProfessionalProfileId(String professionalProfileId) {
		ProfessionalProfileId = professionalProfileId;
	}

	private int Rating;
	private String Fees;
	private String Gender;
	private boolean IsRecordVerified;
	private boolean IsPublished;
	private boolean Verified;
	private boolean IsRecommended;
	private boolean IsPremium;
	private boolean IsRecordVerifiedBySupport;
	private String Type;
	private String About;
	private double Distance;
	private double SearchScore;
	private boolean IncludedInResults;
	private double RelevanceScore;

	private String UniquePublicID;

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
	 * @return the professionalId
	 */
	public int getProfessionalId() {
		return ProfessionalId;
	}

	/**
	 * @param professionalId
	 *            the professionalId to set
	 */
	public void setProfessionalId(int professionalId) {
		ProfessionalId = professionalId;
	}

	/**
	 * @return the facilityId
	 */
	public int getFacilityId() {
		return FacilityId;
	}

	/**
	 * @param facilityId
	 *            the facilityId to set
	 */
	public void setFacilityId(int facilityId) {
		FacilityId = facilityId;
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
	 * @return the contactInformations
	 */
	public List<ContactInformation> getContactInformations() {
		return ContactInformations;
	}

	/**
	 * @param contactInformations
	 *            the contactInformations to set
	 */
	public void setContactInformations(
			List<ContactInformation> contactInformations) {
		ContactInformations = contactInformations;
	}

	/**
	 * @return the specialities
	 */
	public String getSpecialities() {
		String specialities = null;
		if (Specialities != null) {
			specialities = CommonCode.toCommaSeparatedString(Specialities);
			// if (Strings.isNullOrEmpty(specialities)) {
			// specialities = "";
			// }
		}
		return specialities;

	}

	/**
	 * @param specialities
	 *            the specialities to set
	 */
	public void setSpecialities(List<String> specialities) {
		Specialities = specialities;
	}

	/**
	 * @return the servicesOffered
	 */
	public List<String> getServicesOffered() {
		return ServicesOffered;
	}

	/**
	 * @param servicesOffered
	 *            the servicesOffered to set
	 */
	public void setServicesOffered(List<String> servicesOffered) {
		ServicesOffered = servicesOffered;
	}

	/**
	 * @return the dealsAndOffers
	 */
	public List<String> getDealsAndOffers() {
		return DealsAndOffers;
	}

	/**
	 * @param dealsAndOffers
	 *            the dealsAndOffers to set
	 */
	public void setDealsAndOffers(List<String> dealsAndOffers) {
		DealsAndOffers = dealsAndOffers;
	}

	/**
	 * @return the expertise
	 */
	public List<String> getExpertise() {
		return Expertise;
	}

	/**
	 * @param expertise
	 *            the expertise to set
	 */
	public void setExpertise(List<String> expertise) {
		Expertise = expertise;
	}

	/**
	 * @return the educationalQualifications
	 */
	public List<String> getEducationalQualifications() {
		return EducationalQualifications;
	}

	/**
	 * @param educationalQualifications
	 *            the educationalQualifications to set
	 */
	public void setEducationalQualifications(
			List<String> educationalQualifications) {
		EducationalQualifications = educationalQualifications;
	}

	/**
	 * @return the experience
	 */
	public List<String> getExperience() {
		return Experience;
	}

	/**
	 * @param experience
	 *            the experience to set
	 */
	public void setExperience(List<String> experience) {
		Experience = experience;
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
	 * @return the rank
	 */
	public int getRank() {
		return Rank;
	}

	/**
	 * @param rank
	 *            the rank to set
	 */
	public void setRank(int rank) {
		Rank = rank;
	}

	/**
	 * @return the rating
	 */
	public int getRating() {
		return Rating;
	}

	/**
	 * @param rating
	 *            the rating to set
	 */
	public void setRating(int rating) {
		Rating = rating;
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
	 * @return the isRecordVerified
	 */
	public boolean isIsRecordVerified() {
		return IsRecordVerified;
	}

	/**
	 * @param isRecordVerified
	 *            the isRecordVerified to set
	 */
	public void setIsRecordVerified(boolean isRecordVerified) {
		IsRecordVerified = isRecordVerified;
	}

	/**
	 * @return the isPublished
	 */
	public boolean isIsPublished() {
		return IsPublished;
	}

	/**
	 * @param isPublished
	 *            the isPublished to set
	 */
	public void setIsPublished(boolean isPublished) {
		IsPublished = isPublished;
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
	 * @return the type
	 */
	public String getType() {
		return Type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		Type = type;
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
	 * @return the distance
	 */
	public double getDistance() {
		return Distance;
	}

	/**
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(double distance) {
		Distance = distance;
	}

	/**
	 * @return the searchScore
	 */
	public double getSearchScore() {
		return SearchScore;
	}

	/**
	 * @param searchScore
	 *            the searchScore to set
	 */
	public void setSearchScore(double searchScore) {
		SearchScore = searchScore;
	}

	/**
	 * @return the includedInResults
	 */
	public boolean isIncludedInResults() {
		return IncludedInResults;
	}

	/**
	 * @param includedInResults
	 *            the includedInResults to set
	 */
	public void setIncludedInResults(boolean includedInResults) {
		IncludedInResults = includedInResults;
	}

	/**
	 * @return the relevanceScore
	 */
	public double getRelevanceScore() {
		return RelevanceScore;
	}

	/**
	 * @param relevanceScore
	 *            the relevanceScore to set
	 */
	public void setRelevanceScore(double relevanceScore) {
		RelevanceScore = relevanceScore;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the uniqueProfileId
	 */
	public String getUniqueProfileId() {
		return UniqueProfileId;
	}

	/**
	 * @param uniqueProfileId
	 *            the uniqueProfileId to set
	 */
	public void setUniqueProfileId(String uniqueProfileId) {
		UniqueProfileId = uniqueProfileId;
	}

	/**
	 * @return the facilityProfileId
	 */
	public String getFacilityProfileId() {
		return FacilityProfileId;
	}

	/**
	 * @param facilityProfileId
	 *            the facilityProfileId to set
	 */
	public void setFacilityProfileId(String facilityProfileId) {
		FacilityProfileId = facilityProfileId;
	}

	/**
	 * @return the consultationModes
	 */
	public List<String> getConsultationModes() {
		return ConsultationModes;
	}

	/**
	 * @param consultationModes
	 *            the consultationModes to set
	 */
	public void setConsultationModes(List<String> consultationModes) {
		ConsultationModes = consultationModes;
	}

}
