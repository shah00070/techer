package com.schoolteacher.mylibrary.model;

import java.io.Serializable;

public class Member implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;
	private String mobileNumber;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
}
