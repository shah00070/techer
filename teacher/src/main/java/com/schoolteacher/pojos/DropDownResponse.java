package com.schoolteacher.pojos;

public class DropDownResponse {
	private com.schoolteacher.mylibrary.pojo.Status Status;
	private DropDownInfo Data;

	/**
	 * @return the status
	 */
	public com.schoolteacher.mylibrary.pojo.Status getStatus() {
		return Status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(com.schoolteacher.mylibrary.pojo.Status status) {
		Status = status;
	}

	/**
	 * @return the data
	 */
	public DropDownInfo getData() {
		return Data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(DropDownInfo data) {
		Data = data;
	}
}
