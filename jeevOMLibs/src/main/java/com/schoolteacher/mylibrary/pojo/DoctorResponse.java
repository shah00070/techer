package com.schoolteacher.mylibrary.pojo;

import java.io.Serializable;

public class DoctorResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public com.schoolteacher.mylibrary.model.Status Status;
	public DoctorInfo Data;
	private int profile_id;

	/**
	 * @return the status
	 */
	public com.schoolteacher.mylibrary.model.Status getStatus() {
		return Status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(com.schoolteacher.mylibrary.model.Status status) {
		Status = status;
	}

	/**
	 * @return the data
	 */
	public DoctorInfo getData() {
		return Data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(DoctorInfo data) {
		Data = data;
	}

	/**
	 * @return the profile_id
	 */
	public int getProfile_id() {
		return profile_id;
	}

	/**
	 * @param profile_id the profile_id to set
	 */
	public void setProfile_id(int profile_id) {
		this.profile_id = profile_id;
	}
}
