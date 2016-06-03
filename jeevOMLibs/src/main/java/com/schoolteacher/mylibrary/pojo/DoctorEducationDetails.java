package com.schoolteacher.mylibrary.pojo;

import java.io.Serializable;

public class DoctorEducationDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Name;
	private String College;
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
	 * @return the college
	 */
	public String getCollege() {
		return College;
	}
	/**
	 * @param college the college to set
	 */
	public void setCollege(String college) {
		College = college;
	}
	/**
	 * @return the fromYear
	 */
	public int getFromYear() {
		return FromYear;
	}
	/**
	 * @param fromYear the fromYear to set
	 */
	public void setFromYear(int fromYear) {
		FromYear = fromYear;
	}
	/**
	 * @return the toyear
	 */
	public int getToyear() {
		return Toyear;
	}
	/**
	 * @param toyear the toyear to set
	 */
	public void setToyear(int toyear) {
		Toyear = toyear;
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
	 * @return the professionalProfileId
	 */
	public int getProfessionalProfileId() {
		return ProfessionalProfileId;
	}
	/**
	 * @param professionalProfileId the professionalProfileId to set
	 */
	public void setProfessionalProfileId(int professionalProfileId) {
		ProfessionalProfileId = professionalProfileId;
	}
	private int FromYear;
	private int Toyear;
	private String Description;
	private int ProfessionalProfileId;

}
