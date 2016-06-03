package com.schoolteacher.pojos;

import java.io.Serializable;

public class JeevContactInformation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4396825192503236524L;
	public JeevAddress2 getAddress() {
		return Address;
	}

	public void setAddress(JeevAddress2 address) {
		Address = address;
	}

	public String getFacilityName() {
		return FacilityName;
	}

	public void setFacilityName(String facilityName) {
		FacilityName = facilityName;
	}

	public String getFacilityDescription() {
		return FacilityDescription;
	}

	public void setFacilityDescription(String facilityDescription) {
		FacilityDescription = facilityDescription;
	}

	public String getPrimaryPhone() {
		return PrimaryPhone;
	}

	public void setPrimaryPhone(String primaryPhone) {
		PrimaryPhone = primaryPhone;
	}

	public String getSecondaryPhone() {
		return SecondaryPhone;
	}

	public void setSecondaryPhone(String secondaryPhone) {
		SecondaryPhone = secondaryPhone;
	}

	public String getTertiaryPhone() {
		return TertiaryPhone;
	}

	public void setTertiaryPhone(String tertiaryPhone) {
		TertiaryPhone = tertiaryPhone;
	}

	public String getSecondaryEmail() {
		return SecondaryEmail;
	}

	public void setSecondaryEmail(String secondaryEmail) {
		SecondaryEmail = secondaryEmail;
	}

	public String getTertiaryEmail() {
		return TertiaryEmail;
	}

	public void setTertiaryEmail(String tertiaryEmail) {
		TertiaryEmail = tertiaryEmail;
	}

	public String getFees() {
		return Fees;
	}

	public void setFees(String fees) {
		Fees = fees;
	}

	public boolean isIsPrimary() {
		return IsPrimary;
	}

	public void setIsPrimary(boolean isPrimary) {
		IsPrimary = isPrimary;
	}

	private JeevAddress2 Address;
	private String FacilityName;
	private String FacilityDescription;
	private String PrimaryPhone;
	private String SecondaryPhone;
	private String TertiaryPhone;
	private String SecondaryEmail;
	private String TertiaryEmail;
	// private object Availability;
	private String Fees;
	private boolean IsPrimary;

}
