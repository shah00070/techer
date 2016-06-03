package com.schoolteacher.pojos;

import java.io.Serializable;

public class JeevServiceRequestType  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -99143346363899532L;
	private int FacilityServiceRequestTypeId;
	private String FacilityServiceRequestTypeName;
	public int getFacilityServiceRequestTypeId() {
		return FacilityServiceRequestTypeId;
	}
	public void setFacilityServiceRequestTypeId(int facilityServiceRequestTypeId) {
		FacilityServiceRequestTypeId = facilityServiceRequestTypeId;
	}
	public String getFacilityServiceRequestTypeName() {
		return FacilityServiceRequestTypeName;
	}
	public void setFacilityServiceRequestTypeName(
			String facilityServiceRequestTypeName) {
		FacilityServiceRequestTypeName = facilityServiceRequestTypeName;
	}
}
