package com.schoolteacher.pojos;

import com.schoolteacher.mylibrary.model.Status;

import java.util.ArrayList;
import java.util.List;

public class SuggestionsResponse {
	private Status Status;
	private List<Suggestions> Data = new ArrayList<Suggestions>();

	public Status getStatus() {
		return Status;
	}

	public void setStatus(Status Status) {
		this.Status = Status;
	}

	public List<Suggestions> getData() {
		return Data;
	}

	public void setData(List<Suggestions> Data) {
		this.Data = Data;
	}

}