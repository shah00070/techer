package com.schoolteacher.mylibrary.model;

public class DataDictionary {
	public Object UniquePublicID;
	public boolean IsDeleted;
	public int CreatedBy;
	public int UpdatedBy;
	public int Id;
	public String CreatedOnUTC;
	public String UpdatedOnUTC;
	public String Term;
	public String ShortDefinition;
	public String LongDefinition;
	public Object Name;

	public Object getUniquePublicID() {
		return UniquePublicID;
	}

	public void setUniquePublicID(Object uniquePublicID) {
		UniquePublicID = uniquePublicID;
	}

	public boolean isIsDeleted() {
		return IsDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		IsDeleted = isDeleted;
	}

	public int getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(int createdBy) {
		CreatedBy = createdBy;
	}

	public int getUpdatedBy() {
		return UpdatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		UpdatedBy = updatedBy;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getCreatedOnUTC() {
		return CreatedOnUTC;
	}

	public void setCreatedOnUTC(String createdOnUTC) {
		CreatedOnUTC = createdOnUTC;
	}

	public String getUpdatedOnUTC() {
		return UpdatedOnUTC;
	}

	public void setUpdatedOnUTC(String updatedOnUTC) {
		UpdatedOnUTC = updatedOnUTC;
	}

	public String getTerm() {
		return Term;
	}

	public void setTerm(String term) {
		Term = term;
	}

	public String getShortDefinition() {
		return ShortDefinition;
	}

	public void setShortDefinition(String shortDefinition) {
		ShortDefinition = shortDefinition;
	}

	public String getLongDefinition() {
		return LongDefinition;
	}

	public void setLongDefinition(String longDefinition) {
		LongDefinition = longDefinition;
	}

	public Object getName() {
		return Name;
	}

	public void setName(Object name) {
		Name = name;
	}
}
