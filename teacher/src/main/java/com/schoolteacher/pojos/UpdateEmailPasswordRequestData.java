package com.schoolteacher.pojos;

public class UpdateEmailPasswordRequestData {
	private String Email;
	private String CellNumber;
	private int UserId;

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getCellNumber() {
		return CellNumber;
	}

	public void setCellNumber(String cellNumber) {
		CellNumber = cellNumber;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}
}
