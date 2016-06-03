package com.schoolteacher.pojos;

import java.io.Serializable;

public class ProfileExperience implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -940742826311791609L;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getNameofPlace() {
		return NameofPlace;
	}
	public void setNameofPlace(String nameofPlace) {
		NameofPlace = nameofPlace;
	}
	public String getDesignation() {
		return Designation;
	}
	public void setDesignation(String designation) {
		Designation = designation;
	}
	public String getFrom() {
		return From;
	}
	public void setFrom(String from) {
		From = from;
	}
	public String getTo() {
		return To;
	}
	public void setTo(String to) {
		To = to;
	}
	public int getProfessionalProfileId() {
		return ProfessionalProfileId;
	}
	public void setProfessionalProfileId(int professionalProfileId) {
		ProfessionalProfileId = professionalProfileId;
	}
	private int Id;
    private String NameofPlace;
    private String Designation;
    private String From;
    private String To;
	// private object Description;
    private int ProfessionalProfileId;
}
