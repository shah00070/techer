package com.schoolteacher.pojos;

import java.io.Serializable;

public class ProfileServiceRequestType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7325178843188120200L;
	private int ProfessionalServiceRequestTypeId;
	private String ProfessionalServiceRequestTypeName;

	public int getProfessionalServiceRequestTypeId() {
		return ProfessionalServiceRequestTypeId;
	}

	public void setProfessionalServiceRequestTypeId(
			int professionalServiceRequestTypeId) {
		ProfessionalServiceRequestTypeId = professionalServiceRequestTypeId;
	}

	public String getProfessionalServiceRequestTypeName() {
		return ProfessionalServiceRequestTypeName;
	}

	public void setProfessionalServiceRequestTypeName(
			String professionalServiceRequestTypeName) {
		ProfessionalServiceRequestTypeName = professionalServiceRequestTypeName;
	}
}
