package com.schoolteacher.mylibrary.pojo;

import java.io.Serializable;

public class ConsultationMode implements Serializable {

	private static final long serialVersionUID = 6276691045066209579L;
	private int Id;
	private int ProfessionalProfileId;
	private int ConsultationModeId;
	private String ConsultationModeDesc;
	private String Fees;
	/**
	 * @return the id
	 */
	public int getId() {
		return Id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		Id = id;
	}
	/**
	 * @return the professionalProfileId
	 */
	public int getProfessionalProfileId() {
		return ProfessionalProfileId;
	}
	/**
	 * @param professionalProfileId the professionalProfileId to set
	 */
	public void setProfessionalProfileId(int professionalProfileId) {
		ProfessionalProfileId = professionalProfileId;
	}
	/**
	 * @return the consultationModeId
	 */
	public int getConsultationModeId() {
		return ConsultationModeId;
	}
	/**
	 * @param consultationModeId the consultationModeId to set
	 */
	public void setConsultationModeId(int consultationModeId) {
		ConsultationModeId = consultationModeId;
	}
	/**
	 * @return the consultationModeDesc
	 */
	public String getConsultationModeDesc() {
		return ConsultationModeDesc;
	}
	/**
	 * @param consultationModeDesc the consultationModeDesc to set
	 */
	public void setConsultationModeDesc(String consultationModeDesc) {
		ConsultationModeDesc = consultationModeDesc;
	}
	/**
	 * @return the fees
	 */
	public String getFees() {
		return Fees;
	}
	/**
	 * @param fees the fees to set
	 */
	public void setFees(String fees) {
		Fees = fees;
	}

}
