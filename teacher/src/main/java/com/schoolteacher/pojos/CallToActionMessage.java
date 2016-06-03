package com.schoolteacher.pojos;

import java.io.Serializable;

public class CallToActionMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 670820009833090968L;
	private int Id;
	private String Subject;
	private String Message;
	private String ToProfessionalId;
	private String ToFacilityId;
	private String FromMemberId;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getSubject() {
		return Subject;
	}

	public void setSubject(String subject) {
		Subject = subject;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getFromMemberId() {
		return FromMemberId;
	}

	public void setFromMemberId(String fromMemberId) {
		FromMemberId = fromMemberId;
	}

	public String getToProfessionalId() {
		return ToProfessionalId;
	}

	public void setToProfessionalId(String toProfessionalId) {
		ToProfessionalId = toProfessionalId;
	}

	public String getToFacilityId() {
		return ToFacilityId;
	}

	public void setToFacilityId(String toFacilityId) {
		ToFacilityId = toFacilityId;
	}

}
