package com.schoolteacher.pojos;

import com.schoolteacher.mylibrary.pojo.Status;

public class GetPackageResponse {
	private Status Status;
	private PackageInfo Data;
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
	public PackageInfo getData() {
		return Data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(PackageInfo data) {
		Data = data;
	}
}
