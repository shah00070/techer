package com.schoolteacher.mylibrary.model;

import java.io.Serializable;

public class Facility implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Id;
	private String Name;
	private int Count;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getCount() {
		return Count;
	}

	public void setCount(int count) {
		Count = count;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}
}
