package com.schoolteacher.mylibrary.pojo;

import com.google.gson.annotations.Expose;

public class MissedCallDataResponse {
	@Expose
	private Status Status;
	@Expose
	private MissedCallData Data;

	public Status getStatus() {
		return Status;
	}

	public void setStatus(Status Status) {
		this.Status = Status;
	}

	public MissedCallData getData() {
		return Data;
	}

	public void setData(MissedCallData Data) {
		this.Data = Data;
	}

}
