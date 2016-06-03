package com.schoolteacher.pojos;

public class JeevSearch {
	private com.schoolteacher.mylibrary.pojo.Status Status;

	public com.schoolteacher.mylibrary.pojo.Status getStatus() {
		return Status;
	}

	public void setStatus(com.schoolteacher.mylibrary.pojo.Status status) {
		Status = status;
	}

	public JeevSearchInfo getData() {
		return Data;
	}

	public void setData(JeevSearchInfo data) {
		Data = data;
	}

	private JeevSearchInfo Data;

}
