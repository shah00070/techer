package com.schoolteacher.mylibrary.pojo;

public class ConsumerProfilesRespone {
	private Status Status;
	private ConsumerInfo Data;

	
	/**
	 * @return the data
	 */
	public ConsumerInfo getData() {
		return Data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(ConsumerInfo data) {
		Data = data;
	}

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
}
