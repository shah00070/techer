package com.schoolteacher.pojos;

import com.schoolteacher.mylibrary.pojo.Status;

import java.io.Serializable;

public class SearchResultsResponse implements Serializable {

	private static final long serialVersionUID = 4471797312829796346L;
	private Status Status;
	private SearchResultInfo Data;

	public Status getStatus() {
		return Status;
	}

	public void setStatus(Status status) {
		Status = status;
	}

	public SearchResultInfo getData() {
		return Data;
	}

	public void setData(SearchResultInfo data) {
		Data = data;
	}

}
