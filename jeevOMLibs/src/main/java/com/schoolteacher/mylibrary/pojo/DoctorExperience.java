package com.schoolteacher.mylibrary.pojo;

import java.io.Serializable;

public class DoctorExperience implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String NameofPlace;
	private String Designation;
	private String From;
	private String To;
	private String Description;
	private int ProfessionalProfileId;

	/**
	 * @return the nameofPlace
	 */
	public String getNameofPlace() {
		return NameofPlace;
	}

	/**
	 * @param nameofPlace
	 *            the nameofPlace to set
	 */
	public void setNameofPlace(String nameofPlace) {
		NameofPlace = nameofPlace;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return Designation;
	}

	/**
	 * @param designation
	 *            the designation to set
	 */
	public void setDesignation(String designation) {
		Designation = designation;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return From;
	}

	/**
	 * @param from
	 *            the from to set
	 */
	public void setFrom(String from) {
		From = from;
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return To;
	}

	/**
	 * @param to
	 *            the to to set
	 */
	public void setTo(String to) {
		To = to;
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
}
