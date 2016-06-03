package com.schoolteacher.pojos;

public class JeevConveniences {
	private int Id;
	private String Name;
	private String Description;
	private int CreatedBy;
	private int ModifiedBy;
	private String CreatedOnUTC;
	private String ModifiedOnUTC;
	private boolean IsActive;
	private boolean IsDeleted;
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

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
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

	public boolean isIsDeleted() {
		return IsDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		IsDeleted = isDeleted;
	}

	public int getFacilityId() {
		return FacilityId;
	}

	public void setFacilityId(int facilityId) {
		FacilityId = facilityId;
	}

}
