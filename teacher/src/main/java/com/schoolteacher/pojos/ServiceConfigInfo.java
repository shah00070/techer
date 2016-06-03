package com.schoolteacher.pojos;

public class ServiceConfigInfo {
	private com.schoolteacher.mylibrary.pojo.Status Status ;
	private ServiceConfigResult Data;

	public ServiceConfigResult getData() {
		return Data;
	}

	public void setData(ServiceConfigResult data) {
		Data = data;
	}

	public com.schoolteacher.mylibrary.pojo.Status getStatus() {
		return Status;
	}

	public void setStatus(com.schoolteacher.mylibrary.pojo.Status status) {
		Status = status;
	}
}
