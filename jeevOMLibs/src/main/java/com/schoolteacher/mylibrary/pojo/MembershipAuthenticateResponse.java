package com.schoolteacher.mylibrary.pojo;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class MembershipAuthenticateResponse implements Serializable {

	/**
	 * 
	 */
	/*private static final long serialVersionUID = 1L;*/
	@Expose
	private Status Status;
	@Expose
	private MemberShipAuthenticData Data;

	public Status getStatus() {
		return Status;
	}

	public void setStatus(Status Status) {
		this.Status = Status;
	}

	public MemberShipAuthenticData getData() {
		return Data;
	}

	public void setData(MemberShipAuthenticData Data) {
		this.Data = Data;
	}

}
