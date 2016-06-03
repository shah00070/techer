package com.schoolteacher.pojos;

import java.io.Serializable;

public class ConsumerDetailsResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private com.schoolteacher.mylibrary.pojo.Status Status;
	private ConsumerDetails Data;
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
	public ConsumerDetails getData() {
		return Data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(ConsumerDetails data) {
		Data = data;
	}
}
