package com.schoolteacher.mylibrary.model;

import java.io.Serializable;

public class RegistrationDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String Number;
	private String Authority;


	public String getNumber() {
		return Number;
	}

	public void setNumber(String number) {
		Number = number;
	}

	public String getAuthority() {
		return Authority;
	}

	public void setAuthority(String authority) {
		Authority = authority;
	}


}
