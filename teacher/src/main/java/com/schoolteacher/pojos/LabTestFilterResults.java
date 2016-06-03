package com.schoolteacher.pojos;

public class LabTestFilterResults {
	private com.schoolteacher.mylibrary.pojo.Status Status;
	private LabTestData Data;

	public com.schoolteacher.mylibrary.pojo.Status getStatus() {
		return Status;
	}

	public void setStatus(com.schoolteacher.mylibrary.pojo.Status status) {
		Status = status;
	}

	public LabTestData getData() {
		return Data;
	}

	public void setData(LabTestData data) {
		Data = data;
	}
}