package com.schoolteacher.pojos;

import java.io.Serializable;

public class ProfileExpertise implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9192847148274943163L;
	private String Name;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getProfessionalProfileId() {
		return ProfessionalProfileId;
	}

	public void setProfessionalProfileId(int professionalProfileId) {
		ProfessionalProfileId = professionalProfileId;
	}

	// public object Description { get; set; }
	private int ProfessionalProfileId;
}
