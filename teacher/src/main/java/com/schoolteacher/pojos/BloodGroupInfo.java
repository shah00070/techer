package com.schoolteacher.pojos;

import java.util.List;

public class BloodGroupInfo {
	private List<BloodGroup> BloodGroups;
	private int Id;

	/**
	 * @return the bloodGroups
	 */
	public List<BloodGroup> getBloodGroups() {
		return BloodGroups;
	}

	/**
	 * @param bloodGroups
	 *            the bloodGroups to set
	 */
	public void setBloodGroups(List<BloodGroup> bloodGroups) {
		BloodGroups = bloodGroups;
	}

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
}
