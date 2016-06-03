package com.schoolteacher.pojos;

import java.io.Serializable;

public class ProfileSpecialityList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1298548014929220189L;
	private int Id;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	private String Name;
	private String Description;
}
