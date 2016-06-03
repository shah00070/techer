package com.schoolteacher.pojos;

public class EmailConsultationRequest {

	private int ByMemberId;
	private int ForMemberId;
	private String ForName;
	private String ForAge;
	private String ForGender;
	private int ToProfessionalId;
	private int ToFacilityId;
	private String MaskedFields;
	private String Payload;
	/**
	 * @return the byMemberId
	 */
	public int getByMemberId() {
		return ByMemberId;
	}
	/**
	 * @param byMemberId the byMemberId to set
	 */
	public void setByMemberId(int byMemberId) {
		ByMemberId = byMemberId;
	}
	/**
	 * @return the forMemberId
	 */
	public int getForMemberId() {
		return ForMemberId;
	}
	/**
	 * @param forMemberId the forMemberId to set
	 */
	public void setForMemberId(int forMemberId) {
		ForMemberId = forMemberId;
	}
	/**
	 * @return the forName
	 */
	public String getForName() {
		return ForName;
	}
	/**
	 * @param forName the forName to set
	 */
	public void setForName(String forName) {
		ForName = forName;
	}
	/**
	 * @return the forAge
	 */
	public String getForAge() {
		return ForAge;
	}
	/**
	 * @param forAge the forAge to set
	 */
	public void setForAge(String forAge) {
		ForAge = forAge;
	}
	/**
	 * @return the forGender
	 */
	public String getForGender() {
		return ForGender;
	}
	/**
	 * @param forGender the forGender to set
	 */
	public void setForGender(String forGender) {
		ForGender = forGender;
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
	 * @return the maskedFields
	 */
	public String getMaskedFields() {
		return MaskedFields;
	}
	/**
	 * @param maskedFields the maskedFields to set
	 */
	public void setMaskedFields(String maskedFields) {
		MaskedFields = maskedFields;
	}
	/**
	 * @return the payload
	 */
	public String getPayload() {
		return Payload;
	}
	/**
	 * @param payload the payload to set
	 */
	public void setPayload(String payload) {
		Payload = payload;
	}
	
}
