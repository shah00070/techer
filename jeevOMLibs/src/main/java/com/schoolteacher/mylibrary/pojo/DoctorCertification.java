package com.schoolteacher.mylibrary.pojo;

import java.io.Serializable;

public class DoctorCertification implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Name;
	private String Description;
	private int ProfessionalProfileId;

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

	public int getProfessionalProfileId() {
		return ProfessionalProfileId;
	}

	public void setProfessionalProfileId(int professionalProfileId) {
		ProfessionalProfileId = professionalProfileId;
	}

}
