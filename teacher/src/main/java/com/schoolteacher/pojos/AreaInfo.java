package com.schoolteacher.pojos;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class AreaInfo {

	@Expose
	private List<Area> Areas = new ArrayList<Area>();

	public List<Area> getAreas() {
		return Areas;
	}

	public void setAreas(List<Area> areas) {
		Areas = areas;
	}

}
