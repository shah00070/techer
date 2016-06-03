package com.schoolteacher.pojos;

public class EmailResponseInfo {
	private int TotalRecords;
	private int ServiceRequestId;
	private int ServiceRequestMessageId;
	/**
	 * @return the totalRecords
	 */
	public int getTotalRecords() {
		return TotalRecords;
	}
	/**
	 * @param totalRecords the totalRecords to set
	 */
	public void setTotalRecords(int totalRecords) {
		TotalRecords = totalRecords;
	}
	/**
	 * @return the serviceRequestId
	 */
	public int getServiceRequestId() {
		return ServiceRequestId;
	}
	/**
	 * @param serviceRequestId the serviceRequestId to set
	 */
	public void setServiceRequestId(int serviceRequestId) {
		ServiceRequestId = serviceRequestId;
	}
	/**
	 * @return the serviceRequestMessageId
	 */
	public int getServiceRequestMessageId() {
		return ServiceRequestMessageId;
	}
	/**
	 * @param serviceRequestMessageId the serviceRequestMessageId to set
	 */
	public void setServiceRequestMessageId(int serviceRequestMessageId) {
		ServiceRequestMessageId = serviceRequestMessageId;
	}
}
