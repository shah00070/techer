package com.schoolteacher.pojos;

import com.schoolteacher.mylibrary.pojo.Status;

public class UploadImageResponse {

	private Status Status;
	private UploadImageInfo Data;
	/**
	 * @return the status
	 */
	public Status getStatus() {
		return Status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		Status = status;
	}
	/**
	 * @return the data
	 */
	public UploadImageInfo getData() {
		return Data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(UploadImageInfo data) {
		Data = data;
	}
}
