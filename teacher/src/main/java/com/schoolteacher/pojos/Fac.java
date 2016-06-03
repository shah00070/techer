package com.schoolteacher.pojos;

public class Fac {
	private int Id;
	private String Name;
	private ContactInfoFac FacilityContactInformation;

	/**
	 * @return the id
	 */
	public int getId() {
		return Id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		Id = id;
	}

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
	 * @return the facilityContactInformation
	 */
	public ContactInfoFac getFacilityContactInformation() {
		return FacilityContactInformation;
	}

	/**
	 * @param facilityContactInformation the facilityContactInformation to set
	 */
	public void setFacilityContactInformation(ContactInfoFac facilityContactInformation) {
		FacilityContactInformation = facilityContactInformation;
	}
}
