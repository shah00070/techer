package com.schoolteacher.mylibrary.pojo;

import java.io.Serializable;

public class FacilityInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int TotalRecords;
	private FacilityProfile FacilityProfile;

	/**
	 * @return the totalRecords
	 */
	public int getTotalRecords() {
		return TotalRecords;
	}

	/**
	 * @param totalRecords
	 *            the totalRecords to set
	 */
	public void setTotalRecords(int totalRecords) {
		TotalRecords = totalRecords;
	}

	/**
	 * @return the facilityProfile
	 */
	public FacilityProfile getFacilityProfile() {
		return FacilityProfile;
	}

	/**
	 * @param facilityProfile
	 *            the facilityProfile to set
	 */
	public void setFacilityProfile(FacilityProfile facilityProfile) {
		FacilityProfile = facilityProfile;
	}
}
