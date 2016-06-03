package com.schoolteacher.mylibrary.model;

import java.io.Serializable;
import java.util.List;

public class DataContainer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Status Status;
	private Data Data;
	private int UserId;
	private List<Facilities> Facilities;

	public Status getStatus() {
		return Status;
	}

	public void setStatus(Status status) {
		Status = status;
	}

	public Data getData() {
		return Data;
	}

	public void setData(Data data) {
		Data = data;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public List<Facilities> getFacilities() {
		return Facilities;
	}

	public void setFacilities(List<Facilities> facilities) {
		Facilities = facilities;
	}
}
