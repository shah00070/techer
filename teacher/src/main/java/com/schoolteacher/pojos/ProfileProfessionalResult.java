package com.schoolteacher.pojos;

import com.schoolteacher.mylibrary.pojo.Status;

public class ProfileProfessionalResult {

	private Status Stauts;
	private ProfessionalProfileAppointment Data;

	public Status getStauts() {
		return Stauts;
	}

	public void setStauts(Status stauts) {
		Stauts = stauts;
	}

	public ProfessionalProfileAppointment getData() {
		return Data;
	}

	public void setData(ProfessionalProfileAppointment data) {
		Data = data;
	}
}
