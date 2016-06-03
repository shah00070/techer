package com.schoolteacher.mylibrary.model;

import java.io.Serializable;

public class Area implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Id;
	private String Name;
	private int CreatedBy;
	private int ModifiedBy;
	private String CreatedOnUTC;
	private String ModifiedOnUTC;
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
	public int getParentId() {
		return ParentId;
	}
	public void setParentId(int parentId) {
		ParentId = parentId;
	}
	public String getParentName() {
		return ParentName;
	}
	public void setParentName(String parentName) {
		ParentName = parentName;
	}
	private int ParentId;
	private String ParentName;
}
