package com.schoolteacher.mylibrary.model;

import java.io.Serializable;

public class LoadUserResponse implements Serializable {	
	
	private static final long serialVersionUID = 1L;
	private Status Status;
	private MemberInfo Data;
	public Status getStatus() {
		return Status;
	}

	public void setStatus(Status status) {
		Status = status;
	}

	public MemberInfo getData() {
		return Data;
	}

	public void setData(MemberInfo data) {
		Data = data;
	}
}
