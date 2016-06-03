package com.schoolteacher.mylibrary.pojo;

public class CallbackRequestBody {
	private String UserId;
	private String TargetUserId;
	private String ServiceRequestTypeId;
	private String ServiceRequestReasonId;
	private String Message;
	private String Header;

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getTargetUserId() {
		return TargetUserId;
	}

	public void setTargetUserId(String targetUserId) {
		TargetUserId = targetUserId;
	}

	public String getServiceRequestTypeId() {
		return ServiceRequestTypeId;
	}

	public void setServiceRequestTypeId(String serviceRequestTypeId) {
		ServiceRequestTypeId = serviceRequestTypeId;
	}

	public String getServiceRequestReasonId() {
		return ServiceRequestReasonId;
	}

	public void setServiceRequestReasonId(String serviceRequestReasonId) {
		ServiceRequestReasonId = serviceRequestReasonId;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getHeader() {
		return Header;
	}

	public void setHeader(String header) {
		Header = header;
	}

	public boolean isIsUserMessage() {
		return IsUserMessage;
	}

	public void setIsUserMessage(boolean isUserMessage) {
		IsUserMessage = isUserMessage;
	}

	private boolean IsUserMessage;
}
