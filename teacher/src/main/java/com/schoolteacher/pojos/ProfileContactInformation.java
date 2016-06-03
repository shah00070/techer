package com.schoolteacher.pojos;

import java.io.Serializable;

public class ProfileContactInformation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1495174798779955768L;
	public JeevAddress getAddress() {
		return Address;
	}

	public void setAddress(JeevAddress address) {
		Address = address;
	}

	public String getFacilityName() {
		return FacilityName;
	}

	public void setFacilityName(String facilityName) {
		FacilityName = facilityName;
	}

	public String getPrimaryPhone() {
		return PrimaryPhone;
	}

	public void setPrimaryPhone(String primaryPhone) {
		PrimaryPhone = primaryPhone;
	}

	public String getFacilityProfileId() {
		return FacilityProfileId;
	}

	public void setFacilityProfileId(String facilityProfileId) {
		FacilityProfileId = facilityProfileId;
	}

	public String getFees() {
		return Fees;
	}

	public void setFees(String fees) {
		Fees = fees;
	}

	public int getFacilityID() {
		return FacilityID;
	}

	public void setFacilityID(int facilityID) {
		FacilityID = facilityID;
	}

	public boolean isIsPrimary() {
		return IsPrimary;
	}

	public void setIsPrimary(boolean isPrimary) {
		IsPrimary = isPrimary;
	}

	private JeevAddress Address;
	private String FacilityName;
	// private object FacilityDescription ;
	private String PrimaryPhone;
	// private object SecondaryPhone ;
	// private object TertiaryPhone ;
	// private object SecondaryEmail ;
	// private object TertiaryEmail ;
	// private object Availability ;
	// private object ServicesOffered ;
	private String FacilityProfileId;
	private String Fees;
	private int FacilityID;
	private boolean IsPrimary;
}
