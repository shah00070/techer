package com.schoolteacher.pojos;

import java.util.List;

public class FacDetails {
	private String ConsultationFees ;
	private List<Fac> Facilities;
	private List<ConsultationDetail> ConsultationDetails;
	/**
	 * @return the facilities
	 */
	public List<Fac> getFacilities() {
		return Facilities;
	}

	/**
	 * @param facilities the facilities to set
	 */
	public void setFacilities(List<Fac> facilities) {
		Facilities = facilities;
	}

	/**
	 * @return the consultationFees
	 */
	public String getConsultationFees() {
		return ConsultationFees;
	}

	/**
	 * @param consultationFees the consultationFees to set
	 */
	public void setConsultationFees(String consultationFees) {
		ConsultationFees = consultationFees;
	}

	/**
	 * @return the consultationDetails
	 */
	public List<ConsultationDetail> getConsultationDetails() {
		return ConsultationDetails;
	}

	/**
	 * @param consultationDetails the consultationDetails to set
	 */
	public void setConsultationDetails(List<ConsultationDetail> consultationDetails) {
		ConsultationDetails = consultationDetails;
	}
}
