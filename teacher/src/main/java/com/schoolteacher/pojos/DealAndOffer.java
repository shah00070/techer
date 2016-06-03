package com.schoolteacher.pojos;

import java.io.Serializable;

public class DealAndOffer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 240694925881578785L;
	private int Id;
	private String Name;
	// private object Description ;
	private int FacilityId;
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
	private int CreatedBy;
	private int ModifiedBy;
	private String CreatedOnUTC;
	private String ModifiedOnUTC;
	private boolean IsActive;
}
