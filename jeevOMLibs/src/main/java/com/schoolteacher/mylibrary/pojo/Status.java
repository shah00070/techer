package com.schoolteacher.mylibrary.pojo;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class Status implements Serializable {
	/**
	 * 
	 */

	@Expose
	private String Code;
	@Expose
	private String Reason;
	@Expose
	private String Message;

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getReason() {
		return Reason;
	}

	public void setReason(String reason) {
		Reason = reason;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}
}
