package com.schoolteacher.pojos;

import com.schoolteacher.mylibrary.model.Status;

public class EmailRequestResponse {
	private EmailResponseInfo Data;
	private Status Status;

	public Status getStatus() {
		return Status;
	}

	public void setStatus(Status status) {
		Status = status;
	}

	/**
	 * @return the data
	 */
	public EmailResponseInfo getData() {
		return Data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(EmailResponseInfo data) {
		Data = data;
	}
}
