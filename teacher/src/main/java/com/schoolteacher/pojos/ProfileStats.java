package com.schoolteacher.pojos;

import java.io.Serializable;

public class ProfileStats implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1135786854112275551L;
	private String Key;
	private String Value;

	public String getKey() {
		return Key;
	}

	public void setKey(String key) {
		Key = key;
	}

	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}
}
