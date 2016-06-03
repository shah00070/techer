package com.schoolteacher.pojos;

import com.schoolteacher.mylibrary.pojo.Status;

import java.util.List;

public class SystemServiceConfigurationResult {

	private Status Status;
	private List<SystemServiceConfig> Data;

	public Status getStatus() {
		return Status;
	}

	public void setStatus(Status status) {
		Status = status;
	}

	public List<SystemServiceConfig> getData() {
		return Data;
	}

	public void setData(List<SystemServiceConfig> data) {
		Data = data;
	}
}
