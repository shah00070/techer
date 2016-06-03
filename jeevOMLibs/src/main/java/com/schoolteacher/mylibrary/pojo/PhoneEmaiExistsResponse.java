package com.schoolteacher.mylibrary.pojo;

import com.google.gson.annotations.Expose;

public class PhoneEmaiExistsResponse {

	@Expose
	private Status Status;
	@Expose
	private Boolean Data;

	public Status getStatus() {
		return Status;
	}

	public void setStatus(Status status) {
		Status = status;
	}

	public Boolean isData() {
		return Data;
	}

	public void setData(Boolean data) {
		Data = data;
	}

}
