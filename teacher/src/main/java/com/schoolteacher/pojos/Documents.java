package com.schoolteacher.pojos;

public class Documents {
	private com.schoolteacher.mylibrary.pojo.Status Status;
	private DocumentListData Data;

	public com.schoolteacher.mylibrary.pojo.Status getStatus() {
		return Status;
	}

	public void setStatus(com.schoolteacher.mylibrary.pojo.Status status) {
		Status = status;
	}

	public DocumentListData getData() {
		return Data;
	}

	public void setData(DocumentListData data) {
		Data = data;
	}
}
