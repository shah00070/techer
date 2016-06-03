package com.schoolteacher.mylibrary.pojo;

import java.io.Serializable;

public class ClaimContactInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Address Address;
	private String FacilityName;
	private String FacilityDescription;
	private String PrimaryPhone;
	private String SecondaryPhone;
	private String TertiaryPhone;
	private String SecondaryEmail;
	private String TertiaryEmail;
	private String Availability;
	private String Fees;
	private boolean IsPrimary;

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return Address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(Address address) {
		Address = address;
	}

	/**
	 * @return the facilityName
	 */
	public String getFacilityName() {
		return FacilityName;
	}

	/**
	 * @param facilityName
	 *            the facilityName to set
	 */
	public void setFacilityName(String facilityName) {
		FacilityName = facilityName;
	}

	/**
	 * @return the facilityDescription
	 */
	public String getFacilityDescription() {
		return FacilityDescription;
	}

	/**
	 * @param facilityDescription
	 *            the facilityDescription to set
	 */
	public void setFacilityDescription(String facilityDescription) {
		FacilityDescription = facilityDescription;
	}

	/**
	 * @return the primaryPhone
	 */
	public String getPrimaryPhone() {
		return PrimaryPhone;
	}

	/**
	 * @param primaryPhone
	 *            the primaryPhone to set
	 */
	public void setPrimaryPhone(String primaryPhone) {
		PrimaryPhone = primaryPhone;
	}

	/**
	 * @return the secondaryPhone
	 */
	public String getSecondaryPhone() {
		return SecondaryPhone;
	}

	/**
	 * @param secondaryPhone
	 *            the secondaryPhone to set
	 */
	public void setSecondaryPhone(String secondaryPhone) {
		SecondaryPhone = secondaryPhone;
	}

	/**
	 * @return the tertiaryPhone
	 */
	public String getTertiaryPhone() {
		return TertiaryPhone;
	}

	/**
	 * @param tertiaryPhone
	 *            the tertiaryPhone to set
	 */
	public void setTertiaryPhone(String tertiaryPhone) {
		TertiaryPhone = tertiaryPhone;
	}

	/**
	 * @return the secondaryEmail
	 */
	public String getSecondaryEmail() {
		return SecondaryEmail;
	}

	/**
	 * @param secondaryEmail
	 *            the secondaryEmail to set
	 */
	public void setSecondaryEmail(String secondaryEmail) {
		SecondaryEmail = secondaryEmail;
	}

	/**
	 * @return the tertiaryEmail
	 */
	public String getTertiaryEmail() {
		return TertiaryEmail;
	}

	/**
	 * @param tertiaryEmail
	 *            the tertiaryEmail to set
	 */
	public void setTertiaryEmail(String tertiaryEmail) {
		TertiaryEmail = tertiaryEmail;
	}

	/**
	 * @return the availability
	 */
	public String getAvailability() {
		return Availability;
	}

	/**
	 * @param availability
	 *            the availability to set
	 */
	public void setAvailability(String availability) {
		Availability = availability;
	}

	/**
	 * @return the fees
	 */
	public String getFees() {
		return Fees;
	}

	/**
	 * @param fees
	 *            the fees to set
	 */
	public void setFees(String fees) {
		Fees = fees;
	}

	/**
	 * @return the isPrimary
	 */
	public boolean isIsPrimary() {
		return IsPrimary;
	}

	/**
	 * @param isPrimary
	 *            the isPrimary to set
	 */
	public void setIsPrimary(boolean isPrimary) {
		IsPrimary = isPrimary;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
