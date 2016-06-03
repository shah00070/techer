package com.schoolteacher.pojos;

import java.io.Serializable;

public class ProfileEducationDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4663246004831065664L;
	private int EducationalQualificationId;
	private String Name;

	public int getEducationalQualificationId() {
		return EducationalQualificationId;
	}

	public void setEducationalQualificationId(int educationalQualificationId) {
		EducationalQualificationId = educationalQualificationId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getFromYear() {
		return FromYear;
	}

	public void setFromYear(int fromYear) {
		FromYear = fromYear;
	}

	public int getToyear() {
		return Toyear;
	}

	public void setToyear(int toyear) {
		Toyear = toyear;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public int getProfessionalProfileId() {
		return ProfessionalProfileId;
	}

	public void setProfessionalProfileId(int professionalProfileId) {
		ProfessionalProfileId = professionalProfileId;
	}

	// private object College ;
	private int FromYear;
	private int Toyear;
	private String Description;
	private int ProfessionalProfileId;
}
