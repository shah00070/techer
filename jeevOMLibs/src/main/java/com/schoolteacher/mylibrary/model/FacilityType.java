package com.schoolteacher.mylibrary.model;

import java.io.Serializable;

public class FacilityType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Id;
	private String Name;
	private int Count;
	// private object SortOrder;
	private int CreatedBy;
	// private object UniqueprivateID;
	// private object IsDeleted;
	private String CreatedOnUTC;
	private int UpdatedBy;
	private String UpdatedOnUTC;
	private boolean IsMedical;
	private String Description;

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

	public int getCount() {
		return Count;
	}

	public void setCount(int count) {
		Count = count;
	}

	public int getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(int createdBy) {
		CreatedBy = createdBy;
	}

	public String getCreatedOnUTC() {
		return CreatedOnUTC;
	}

	public void setCreatedOnUTC(String createdOnUTC) {
		CreatedOnUTC = createdOnUTC;
	}

	public int getUpdatedBy() {
		return UpdatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		UpdatedBy = updatedBy;
	}

	public String getUpdatedOnUTC() {
		return UpdatedOnUTC;
	}

	public void setUpdatedOnUTC(String updatedOnUTC) {
		UpdatedOnUTC = updatedOnUTC;
	}

	public boolean isIsMedical() {
		return IsMedical;
	}

	public void setIsMedical(boolean isMedical) {
		IsMedical = isMedical;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

}
