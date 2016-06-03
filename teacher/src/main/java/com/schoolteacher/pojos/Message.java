package com.schoolteacher.pojos;

import java.io.Serializable;

public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Id;
	private String Subject;
	private String Message;
	private int FromMemberId;
	private int FromProfessionalId;
	private String FromSupportName;
	private int FromFacilityId;
	private String CreatedOnUtc;
	private String FromProfessionalName;
	private String FromProfessionalPhoto;
	private String FromFacilityPhoto;
	private String FromConsumerName;
	private String FromConsumerPhoto;

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

	public int getFromMemberId() {
		return FromMemberId;
	}

	public void setFromMemberId(int fromMemberId) {
		FromMemberId = fromMemberId;
	}

	public String getFromSupportName() {
		return FromSupportName;
	}

	public void setFromSupportName(String fromSupportName) {
		FromSupportName = fromSupportName;
	}

	public String getCreatedOnUtc() {
		return CreatedOnUtc;
	}

	public void setCreatedOnUtc(String createdOnUtc) {
		CreatedOnUtc = createdOnUtc;
	}

	public String getFromProfessionalName() {
		return FromProfessionalName;
	}

	public void setFromProfessionalName(String fromProfessionalName) {
		FromProfessionalName = fromProfessionalName;
	}

	public String getFromConsumerName() {
		return FromConsumerName;
	}

	public void setFromConsumerName(String fromConsumerName) {
		FromConsumerName = fromConsumerName;
	}

	public String getFromConsumerPhoto() {
		return FromConsumerPhoto;
	}

	public void setFromConsumerPhoto(String fromConsumerPhoto) {
		FromConsumerPhoto = fromConsumerPhoto;
	}

	public int getFromProfessionalId() {
		return FromProfessionalId;
	}

	public void setFromProfessionalId(int fromProfessionalId) {
		FromProfessionalId = fromProfessionalId;
	}

	public int getFromFacilityId() {
		return FromFacilityId;
	}

	public void setFromFacilityId(int fromFacilityId) {
		FromFacilityId = fromFacilityId;
	}

	public String getFromProfessionalPhoto() {
		return FromProfessionalPhoto;
	}

	public void setFromProfessionalPhoto(String fromProfessionalPhoto) {
		FromProfessionalPhoto = fromProfessionalPhoto;
	}

	public String getFromFacilityPhoto() {
		return FromFacilityPhoto;
	}

	public void setFromFacilityPhoto(String fromFacilityPhoto) {
		FromFacilityPhoto = fromFacilityPhoto;
	}
}
