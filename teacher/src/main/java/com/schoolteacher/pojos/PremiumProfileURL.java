package com.schoolteacher.pojos;

import java.io.Serializable;

public class PremiumProfileURL implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1428815762599934209L;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getProfessionalProfileId() {
		return ProfessionalProfileId;
	}
	public void setProfessionalProfileId(int professionalProfileId) {
		ProfessionalProfileId = professionalProfileId;
	}
	public int getProfileType() {
		return ProfileType;
	}
	public void setProfileType(int profileType) {
		ProfileType = profileType;
	}
	public String getProfileURL() {
		return ProfileURL;
	}
	public void setProfileURL(String profileURL) {
		ProfileURL = profileURL;
	}
	public String getKeywordTag() {
		return KeywordTag;
	}
	public void setKeywordTag(String keywordTag) {
		KeywordTag = keywordTag;
	}
	public String getMetaDescriptionTag() {
		return MetaDescriptionTag;
	}
	public void setMetaDescriptionTag(String metaDescriptionTag) {
		MetaDescriptionTag = metaDescriptionTag;
	}
	public String getProfileKeywords() {
		return ProfileKeywords;
	}
	public void setProfileKeywords(String profileKeywords) {
		ProfileKeywords = profileKeywords;
	}
	public boolean isIsActive() {
		return IsActive;
	}
	public void setIsActive(boolean isActive) {
		IsActive = isActive;
	}
	public String getValidTill() {
		return ValidTill;
	}
	public void setValidTill(String validTill) {
		ValidTill = validTill;
	}
	private int Id;
	private int ProfessionalProfileId;
	// private object FacilityId;
	// private object ProfessionalProfileprivateId;
	// private object FacilityprivateId;
	private int ProfileType;
	private String ProfileURL;
	private String KeywordTag;
	private String MetaDescriptionTag;
	private String ProfileKeywords;
	private boolean IsActive;
	private String ValidTill;
}
