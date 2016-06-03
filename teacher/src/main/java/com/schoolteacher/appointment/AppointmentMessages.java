package com.schoolteacher.appointment;

public class AppointmentMessages {
	private int Id;
	private String Subject;
	private String Message;
	private long FromMemberId;
	private long FromProfessionalId;
	private long FromFacilityId;
	private long ToMemberId;
	private long ToProfessionalId;
	private long ToFacilityId;
	private String CreatedOnUtc;
	private String UpdatedOnUtc;

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

	public long getFromMemberId() {
		return FromMemberId;
	}

	public void setFromMemberId(long fromMemberId) {
		FromMemberId = fromMemberId;
	}

	public long getFromProfessionalId() {
		return FromProfessionalId;
	}

	public void setFromProfessionalId(long fromProfessionalId) {
		FromProfessionalId = fromProfessionalId;
	}

	public long getFromFacilityId() {
		return FromFacilityId;
	}

	public void setFromFacilityId(long fromFacilityId) {
		FromFacilityId = fromFacilityId;
	}

	public long getToMemberId() {
		return ToMemberId;
	}

	public void setToMemberId(long toMemberId) {
		ToMemberId = toMemberId;
	}

	public long getToProfessionalId() {
		return ToProfessionalId;
	}

	public void setToProfessionalId(long toProfessionalId) {
		ToProfessionalId = toProfessionalId;
	}

	public long getToFacilityId() {
		return ToFacilityId;
	}

	public void setToFacilityId(long toFacilityId) {
		ToFacilityId = toFacilityId;
	}

	public String getCreatedOnUtc() {
		return CreatedOnUtc;
	}

	public void setCreatedOnUtc(String createdOnUtc) {
		CreatedOnUtc = createdOnUtc;
	}

	public String getUpdatedOnUtc() {
		return UpdatedOnUtc;
	}

	public void setUpdatedOnUtc(String updatedOnUtc) {
		UpdatedOnUtc = updatedOnUtc;
	}

}
