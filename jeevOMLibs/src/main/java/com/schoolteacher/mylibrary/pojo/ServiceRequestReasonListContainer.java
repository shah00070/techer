package com.schoolteacher.mylibrary.pojo;

import java.util.List;

public class ServiceRequestReasonListContainer {
	private int TotalRecords;
	private String ServiceRequestTypeList;
	private List<ServiceRequestReasonList> ServiceRequestReasonList;
	private String MessageFormat;
	private int ServiceRequestId;
	private int ServiceRequestMessageId;
	private String ServiceRequestList;
	private String ServiceRequestMessageList;

	public int getTotalRecords() {
		return TotalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		TotalRecords = totalRecords;
	}

	public String getServiceRequestTypeList() {
		return ServiceRequestTypeList;
	}

	public void setServiceRequestTypeList(String serviceRequestTypeList) {
		ServiceRequestTypeList = serviceRequestTypeList;
	}

	public List<ServiceRequestReasonList> getServiceRequestReasonList() {
		return ServiceRequestReasonList;
	}

	public void setServiceRequestReasonList(List<ServiceRequestReasonList> serviceRequestReasonList) {
		ServiceRequestReasonList = serviceRequestReasonList;
	}

	public String getMessageFormat() {
		return MessageFormat;
	}

	public void setMessageFormat(String messageFormat) {
		MessageFormat = messageFormat;
	}

	public int getServiceRequestId() {
		return ServiceRequestId;
	}

	public void setServiceRequestId(int serviceRequestId) {
		ServiceRequestId = serviceRequestId;
	}

	public int getServiceRequestMessageId() {
		return ServiceRequestMessageId;
	}

	public void setServiceRequestMessageId(int serviceRequestMessageId) {
		ServiceRequestMessageId = serviceRequestMessageId;
	}

	public String getServiceRequestList() {
		return ServiceRequestList;
	}

	public void setServiceRequestList(String serviceRequestList) {
		ServiceRequestList = serviceRequestList;
	}

	public String getServiceRequestMessageList() {
		return ServiceRequestMessageList;
	}

	public void setServiceRequestMessageList(String serviceRequestMessageList) {
		ServiceRequestMessageList = serviceRequestMessageList;
	}
}
