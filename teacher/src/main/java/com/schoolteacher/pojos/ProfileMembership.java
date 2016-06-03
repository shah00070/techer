package com.schoolteacher.pojos;

import java.io.Serializable;

public class ProfileMembership implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4819752106789946781L;
	private String Name;

	// public string Description { get; set; }
	// public int ProfessionalProfileId { get; set; }

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
}
