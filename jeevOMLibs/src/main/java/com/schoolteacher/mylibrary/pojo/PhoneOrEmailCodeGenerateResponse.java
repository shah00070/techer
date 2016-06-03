package com.schoolteacher.mylibrary.pojo;

import com.google.gson.annotations.Expose;

public class PhoneOrEmailCodeGenerateResponse {
	@Expose
	private Status Status;
	@Expose
	private String Data;

	public Status getStatus() {
		return Status;
	}

	public void setStatus(Status Status) {
		this.Status = Status;
	}

	public String getData() {
		return Data;
	}

	public void setData(String Data) {
		this.Data = Data;
	}

}
