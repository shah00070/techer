package com.schoolteacher.pojos;

import java.io.Serializable;

public class FamilyMembersResponse implements Serializable {
	private com.schoolteacher.mylibrary.pojo.Status Status;
	private FamilyMembersInfo Data;
	/**
	 * @return the status
	 */
	public com.schoolteacher.mylibrary.pojo.Status getStatus() {
		return Status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(com.schoolteacher.mylibrary.pojo.Status status) {
		Status = status;
	}
	/**
	 * @return the data
	 */
	public FamilyMembersInfo getData() {
		return Data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(FamilyMembersInfo data) {
		Data = data;
	}
}
