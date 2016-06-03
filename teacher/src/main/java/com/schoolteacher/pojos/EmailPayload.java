package com.schoolteacher.pojos;

public class EmailPayload {
	private int FromMemberId;
	private int ToProfessionalId;
	private int ToFacilityId;
	private String Header;
	private String Message;
	/**
	 * @return the fromMemberId
	 */
	public int getFromMemberId() {
		return FromMemberId;
	}
	/**
	 * @param fromMemberId the fromMemberId to set
	 */
	public void setFromMemberId(int fromMemberId) {
		FromMemberId = fromMemberId;
	}
	/**
	 * @return the toProfessionalId
	 */
	public int getToProfessionalId() {
		return ToProfessionalId;
	}
	/**
	 * @param toProfessionalId the toProfessionalId to set
	 */
	public void setToProfessionalId(int toProfessionalId) {
		ToProfessionalId = toProfessionalId;
	}
	/**
	 * @return the toFacilityId
	 */
	public int getToFacilityId() {
		return ToFacilityId;
	}
	/**
	 * @param toFacilityId the toFacilityId to set
	 */
	public void setToFacilityId(int toFacilityId) {
		ToFacilityId = toFacilityId;
	}
	/**
	 * @return the header
	 */
	public String getHeader() {
		return Header;
	}
	/**
	 * @param header the header to set
	 */
	public void setHeader(String header) {
		Header = header;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return Message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		Message = message;
	}

}
