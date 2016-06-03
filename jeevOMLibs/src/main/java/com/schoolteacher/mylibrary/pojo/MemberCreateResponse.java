package com.schoolteacher.mylibrary.pojo;

public class MemberCreateResponse {
	private Status Status;
	private MemberCreate Data;
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
	public MemberCreate getData() {
		return Data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(MemberCreate data) {
		Data = data;
	}
}
