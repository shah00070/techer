package com.schoolteacher.pojos;

public class DocumentListResult {
	private com.schoolteacher.mylibrary.pojo.Status Status;

	public com.schoolteacher.mylibrary.pojo.Status getStatus() {
		return Status;
	}

	public void setStatus(com.schoolteacher.mylibrary.pojo.Status status) {
		Status = status;
	}

	public DocumentTypeListData getData() {
		return Data;
	}

	public void setData(DocumentTypeListData data) {
		Data = data;
	}

	private DocumentTypeListData Data;
}
