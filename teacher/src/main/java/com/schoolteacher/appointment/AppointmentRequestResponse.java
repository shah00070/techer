package com.schoolteacher.appointment;

import com.schoolteacher.mylibrary.pojo.Status;

public class AppointmentRequestResponse {
	private Status Status;
	private AppointmentRequestData Data;

	public Status getStatus() {
		return Status;
	}

	public void setStatus(Status status) {
		Status = status;
	}

	public AppointmentRequestData getData() {
		return Data;
	}

	public void setData(AppointmentRequestData data) {
		Data = data;
	}

}
