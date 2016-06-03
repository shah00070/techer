package com.schoolteacher.pojos;

import java.io.Serializable;

public class ProfileDealAndOffer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4183654754660536935L;
	// private int Id { get; set; }
	private String Name;
	// private object Description { get; set; }
	// private int ProfessionalProfileId { get; set; }
	// private boolean IsActive { get; set; }

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
}
