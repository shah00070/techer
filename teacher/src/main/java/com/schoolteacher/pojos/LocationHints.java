package com.schoolteacher.pojos;

import com.schoolteacher.mylibrary.model.Status;

import java.util.ArrayList;
import java.util.List;

public class LocationHints {
	private Status Status;
	private List<String> Data = new ArrayList<String>();

	public Status getStatus() {
		return Status;
	}

	public void setStatus(Status Status) {
		this.Status = Status;
	}

	public List<String> getData() {
		return Data;
	}

	public void setData(List<String> data) {
		Data = data;
	}

}
