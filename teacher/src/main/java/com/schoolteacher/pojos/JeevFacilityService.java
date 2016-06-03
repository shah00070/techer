package com.schoolteacher.pojos;

import java.io.Serializable;

public class JeevFacilityService implements Serializable {
	private static final long serialVersionUID = 6884653748601952469L;
	private String Name;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getFacilityId() {
		return FacilityId;
	}

	public void setFacilityId(int facilityId) {
		FacilityId = facilityId;
	}

	private int FacilityId;
}
