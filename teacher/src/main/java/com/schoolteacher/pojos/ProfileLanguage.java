package com.schoolteacher.pojos;

import java.io.Serializable;

public class ProfileLanguage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7933372422130256934L;
	public int getLanguageId() {
		return LanguageId;
	}
	public void setLanguageId(int languageId) {
		LanguageId = languageId;
	}
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
	private int LanguageId ;
    private String Name ;
    private String Description ;
	private int ProfessionalProfileId;
}
