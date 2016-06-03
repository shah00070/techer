package com.schoolteacher.mylibrary.pojo;

import java.io.Serializable;

public class SocialMediaInformation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int SocialMediaTypeId;
	public String SocialMediaTypeName;
	public String Description;
	public String SocailMediaTypeDescription;

	public int getSocialMediaTypeId() {
		return SocialMediaTypeId;
	}

	public void setSocialMediaTypeId(int socialMediaTypeId) {
		SocialMediaTypeId = socialMediaTypeId;
	}

	public String getSocialMediaTypeName() {
		return SocialMediaTypeName;
	}

	public void setSocialMediaTypeName(String socialMediaTypeName) {
		SocialMediaTypeName = socialMediaTypeName;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getSocailMediaTypeDescription() {
		return SocailMediaTypeDescription;
	}

	public void setSocailMediaTypeDescription(String socailMediaTypeDescription) {
		SocailMediaTypeDescription = socailMediaTypeDescription;
	}

}
