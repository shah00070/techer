package com.schoolteacher.mylibrary.pojo;

import java.io.Serializable;

public class DoctorRegistrationDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Number;
	private String Authority;

	/**
	 * @return the number
	 */
	public String getNumber() {
		return Number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(String number) {
		Number = number;
	}

	/**
	 * @return the authority
	 */
	public String getAuthority() {
		return Authority;
	}

	/**
	 * @param authority
	 *            the authority to set
	 */
	public void setAuthority(String authority) {
		Authority = authority;
	}

	/**
	 * @return the registrationDate
	 */
	public int getRegistrationDate() {
		return RegistrationDate;
	}

	/**
	 * @param registrationDate
	 *            the registrationDate to set
	 */
	public void setRegistrationDate(int registrationDate) {
		RegistrationDate = registrationDate;
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

	private int RegistrationDate;
	private int ProfessionalProfileId;
}
