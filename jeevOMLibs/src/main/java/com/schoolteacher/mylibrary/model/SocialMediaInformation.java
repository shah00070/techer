package com.schoolteacher.mylibrary.model;

import java.io.Serializable;

public class SocialMediaInformation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Id;
	private int UniqueprivateID;
	private String Name;
	private boolean IsDeleted;
	private String CreatedOnUTC;
	private int CreatedBy;
	private String UpdatedOnUTC;
	private int UpdatedBy;
	private int SocialMediaTypeId;
	private String SocialMediaTypeName;
	private String Description;
	private String SocailMediaTypeDescription;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getUniqueprivateID() {
		return UniqueprivateID;
	}

	public void setUniqueprivateID(int uniqueprivateID) {
		UniqueprivateID = uniqueprivateID;
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
