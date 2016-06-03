package com.schoolteacher.mylibrary.pojo;

import java.io.Serializable;

public class FacilityDealAndOffer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Id;
	private String Name;
	private String Description;
	private int FacilityId;
	private int CreatedBy;
	private int ModifiedBy;
	private String CreatedOnUTC;
	private String ModifiedOnUTC;
	private boolean IsActive;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public int getFacilityId() {
		return FacilityId;
	}

	public void setFacilityId(int facilityId) {
		FacilityId = facilityId;
	}

	public int getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(int createdBy) {
		CreatedBy = createdBy;
	}

	public int getModifiedBy() {
		return ModifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		ModifiedBy = modifiedBy;
	}

	public String getCreatedOnUTC() {
		return CreatedOnUTC;
	}

	public void setCreatedOnUTC(String createdOnUTC) {
		CreatedOnUTC = createdOnUTC;
	}

	public String getModifiedOnUTC() {
		return ModifiedOnUTC;
	}

	public void setModifiedOnUTC(String modifiedOnUTC) {
		ModifiedOnUTC = modifiedOnUTC;
	}

	public boolean isIsActive() {
		return IsActive;
	}

	public void setIsActive(boolean isActive) {
		IsActive = isActive;
	}

}
