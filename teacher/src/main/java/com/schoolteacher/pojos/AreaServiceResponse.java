package com.schoolteacher.pojos;

import com.google.gson.annotations.Expose;
import com.schoolteacher.mylibrary.model.Status;

public class AreaServiceResponse {

	@Expose
	private com.schoolteacher.mylibrary.model.Status Status;
	@Expose
	private AreaInfo Data;

	public AreaInfo getData() {
		return Data;
	}

	public void setData(AreaInfo data) {
		Data = data;
	}

	/**
	 * 
	 * @return The Status
	 */
	public Status getStatus() {
		return Status;
	}

	/**
	 * 
	 * @param Status
	 *            The Status
	 */
	public void setStatus(Status Status) {
		this.Status = Status;
	}

}
