package com.schoolteacher.pojos;

import java.io.Serializable;

public class AwardsAndMention implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4609140331504717280L;
	private int Id;
    private String Name;
	// private object Description;
	// private object ProfessionalProfileId;
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
	public int getYear() {
		return Year;
	}
	public void setYear(int year) {
		Year = year;
	}
	private int Year;
}
