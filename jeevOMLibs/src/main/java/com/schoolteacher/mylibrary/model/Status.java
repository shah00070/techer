package com.schoolteacher.mylibrary.model;

import java.io.Serializable;

public class Status  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Code;
    private String Reason;
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
