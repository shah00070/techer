package com.schoolteacher.pojos;

public class JeevFacilityResult {
	public com.schoolteacher.mylibrary.pojo.Status Status;
	public JeevFacilityInfo Data;

	public com.schoolteacher.mylibrary.pojo.Status getStatus() {
		return Status;
	}

	public void setStatus(com.schoolteacher.mylibrary.pojo.Status status) {
		Status = status;
	}

	public JeevFacilityInfo getData() {
		return Data;
	}

	public void setData(JeevFacilityInfo data) {
		Data = data;
	}
}
