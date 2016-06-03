package com.schoolteacher.pojos;

import java.io.Serializable;

public class SRDocumentList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3243949337105511273L;
	private int Id;
	private int Type;
	private String Name;
	private String Date;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getType() {
		return Type;
	}

	public void setType(int type) {
		Type = type;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
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

	public String getCreatedOnUtc() {
		return CreatedOnUtc;
	}

	public void setCreatedOnUtc(String createdOnUtc) {
		CreatedOnUtc = createdOnUtc;
	}

	public String getUpdatedOnUtc() {
		return UpdatedOnUtc;
	}

	public void setUpdatedOnUtc(String updatedOnUtc) {
		UpdatedOnUtc = updatedOnUtc;
	}

	public String getCreatedByName() {
		return CreatedByName;
	}

	public void setCreatedByName(String createdByName) {
		CreatedByName = createdByName;
	}

	private int CreatedBy;
	private int UpdatedBy;
	private String CreatedOnUtc;
	private String UpdatedOnUtc;
	private String CreatedByName;
}
