package com.schoolteacher.pojos;

import com.schoolteacher.mylibrary.pojo.Status;

public class UpdateConsumerResponse {

	private Status Status;
	private UpdateConsumerResult Data;
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
	public UpdateConsumerResult getData() {
		return Data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(UpdateConsumerResult data) {
		Data = data;
	}
	
}
