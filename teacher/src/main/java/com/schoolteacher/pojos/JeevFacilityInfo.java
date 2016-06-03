package com.schoolteacher.pojos;

import java.io.Serializable;

public class JeevFacilityInfo implements Serializable {
	private static final long serialVersionUID = 6624133006565240741L;
	// private object Facilities { get; set; }
	// private object FacilityList { get; set; }
	// private int TotalRecords { get; set; }
	private JeevFacilityProfile FacilityProfile;

	public JeevFacilityProfile getFacilityProfile() {
		return FacilityProfile;
	}

	public void setFacilityProfile(JeevFacilityProfile facilityProfile) {
		FacilityProfile = facilityProfile;
	}
}
