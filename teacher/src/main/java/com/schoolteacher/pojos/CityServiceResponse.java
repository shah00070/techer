package com.schoolteacher.pojos;

import com.google.gson.annotations.Expose;
import com.schoolteacher.mylibrary.model.Status;

public class CityServiceResponse {

	@Expose
	private com.schoolteacher.mylibrary.model.Status Status;
	@Expose
	private CitiesInfo Data;

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

	/**
	 * 
	 * @return The Data
	 */
	public CitiesInfo getData() {
		return Data;
	}

	/**
	 * 
	 * @param Data
	 *            The Data
	 */
	public void setData(CitiesInfo Data) {
		this.Data = Data;
	}

}
