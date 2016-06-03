package com.schoolteacher.pojos;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class CitiesInfo {

	@Expose
	private List<City> Cities = new ArrayList<City>();

	/**
	 * 
	 * @return The Cities
	 */
	public List<City> getCities() {
		return Cities;
	}

	/**
	 * 
	 * @param Cities
	 *            The Cities
	 */
	public void setCities(List<City> Cities) {
		this.Cities = Cities;
	}

}
