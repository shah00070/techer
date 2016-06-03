package com.schoolteacher.mylibrary.pojo;

public class AddUserRequest {

	private String Email;
	private String CellNumber;

	public AddUserRequest(String email, String mobile) {
		this.Email = email;
		this.CellNumber = mobile;
	}
}
