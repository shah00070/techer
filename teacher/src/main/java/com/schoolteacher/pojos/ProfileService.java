package com.schoolteacher.pojos;

import java.io.Serializable;

public class ProfileService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -784874318804529461L;
	public String Name;
	// public object Description { get; set; }
	// public int ProfessionalProfileId { get; set; }

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
}
