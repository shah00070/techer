package com.schoolteacher.mylibrary.pojo;

public class UpdateCellEmailRequest {
	
	private int UserId;
	private String CellNumber;
	private String Email;
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public String getCellNumber() {
		return CellNumber;
	}
	public void setCellNumber(String cellNumber) {
		CellNumber = cellNumber;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
		 
}
