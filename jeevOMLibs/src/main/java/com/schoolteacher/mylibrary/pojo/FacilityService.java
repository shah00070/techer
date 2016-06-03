package com.schoolteacher.mylibrary.pojo;

import java.io.Serializable;

public class FacilityService implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Name;
	private String Description;
	private int FacilityId;
	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return Description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		Description = description;
	}
	/**
	 * @return the facilityId
	 */
	public int getFacilityId() {
		return FacilityId;
	}
	/**
	 * @param facilityId the facilityId to set
	 */
	public void setFacilityId(int facilityId) {
		FacilityId = facilityId;
	}
}
