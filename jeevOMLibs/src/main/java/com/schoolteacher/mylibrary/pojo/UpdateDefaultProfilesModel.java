package com.schoolteacher.mylibrary.pojo;

public class UpdateDefaultProfilesModel {
	private String DefaultProfileId;
	private String DefaultProfileType;

	/**
	 * @return the defaultProfileType
	 */
	public String getDefaultProfileType() {
		return DefaultProfileType;
	}
	/**
	 * @param defaultProfileType the defaultProfileType to set
	 */
	public void setDefaultProfileType(String defaultProfileType) {
		DefaultProfileType = defaultProfileType;
	}
	/**
	 * @return the defaultProfileId
	 */
	public String getDefaultProfileId() {
		return DefaultProfileId;
	}
	/**
	 * @param defaultProfileId the defaultProfileId to set
	 */
	public void setDefaultProfileId(String defaultProfileId) {
		DefaultProfileId = defaultProfileId;
	}
}
