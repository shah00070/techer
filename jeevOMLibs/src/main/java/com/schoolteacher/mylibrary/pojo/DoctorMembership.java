package com.schoolteacher.mylibrary.pojo;

import java.io.Serializable;

public class DoctorMembership implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Name;
	private String Description;

	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @param name
	 *            the name to set
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
	 * @param description
	 *            the description to set
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
	 * @param professionalProfileId
	 *            the professionalProfileId to set
	 */
	public void setProfessionalProfileId(int professionalProfileId) {
		ProfessionalProfileId = professionalProfileId;
	}

	private int ProfessionalProfileId;
}
