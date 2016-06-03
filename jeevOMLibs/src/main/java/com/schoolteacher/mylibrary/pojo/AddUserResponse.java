package com.schoolteacher.mylibrary.pojo;

public class AddUserResponse {

	private AddUserData Data;

	private Status Status;

	public AddUserData getData() {
		return Data;
	}

	public void setData(AddUserData data) {
		Data = data;
	}

	public Status getStatus() {
		return Status;
	}

	public void setStatus(Status status) {
		Status = status;
	}
}
