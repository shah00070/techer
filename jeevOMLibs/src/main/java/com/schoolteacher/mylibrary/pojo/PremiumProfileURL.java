package com.schoolteacher.mylibrary.pojo;

public class PremiumProfileURL {
	private int Id;
	private int ProfessionalProfileId;
	// public object FacilityId ;
	private int ProfileType;
	private String ProfileURL;
	private String ProfileKeywords;
	private boolean IsActive;
	private String ValidTill;
	/**
	 * @return the id
	 */
	public int getId() {
		return Id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		Id = id;
	}
	/**
	 * @return the professionalProfileId
	 */
	public int getProfessionalProfileId() {
		return ProfessionalProfileId;
	}
	/**
	 * @param professionalProfileId the professionalProfileId to set
	 */
	public void setProfessionalProfileId(int professionalProfileId) {
		ProfessionalProfileId = professionalProfileId;
	}
	/**
	 * @return the profileType
	 */
	public int getProfileType() {
		return ProfileType;
	}
	/**
	 * @param profileType the profileType to set
	 */
	public void setProfileType(int profileType) {
		ProfileType = profileType;
	}
	/**
	 * @return the profileURL
	 */
	public String getProfileURL() {
		return ProfileURL;
	}
	/**
	 * @param profileURL the profileURL to set
	 */
	public void setProfileURL(String profileURL) {
		ProfileURL = profileURL;
	}
	/**
	 * @return the profileKeywords
	 */
	public String getProfileKeywords() {
		return ProfileKeywords;
	}
	/**
	 * @param profileKeywords the profileKeywords to set
	 */
	public void setProfileKeywords(String profileKeywords) {
		ProfileKeywords = profileKeywords;
	}
	/**
	 * @return the isActive
	 */
	public boolean isIsActive() {
		return IsActive;
	}
	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(boolean isActive) {
		IsActive = isActive;
	}
	/**
	 * @return the validTill
	 */
	public String getValidTill() {
		return ValidTill;
	}
	/**
	 * @param validTill the validTill to set
	 */
	public void setValidTill(String validTill) {
		ValidTill = validTill;
	}
}
