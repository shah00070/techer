package com.schoolteacher.mylibrary.pojo;

public class ClaimRequestBody {
	private String UserId;
	private String TargetUserId;
	private String ServiceRequestTypeId;
	private String ServiceRequestReasonId;
	private String Message;
	private String Header;
	private boolean IsUserMessage;
	private String TargetUserType;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return UserId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		UserId = userId;
	}

	/**
	 * @return the targetUserId
	 */
	public String getTargetUserId() {
		return TargetUserId;
	}

	/**
	 * @param targetUserId
	 *            the targetUserId to set
	 */
	public void setTargetUserId(String targetUserId) {
		TargetUserId = targetUserId;
	}

	/**
	 * @return the serviceRequestTypeId
	 */
	public String getServiceRequestTypeId() {
		return ServiceRequestTypeId;
	}

	/**
	 * @param serviceRequestTypeId
	 *            the serviceRequestTypeId to set
	 */
	public void setServiceRequestTypeId(String serviceRequestTypeId) {
		ServiceRequestTypeId = serviceRequestTypeId;
	}

	/**
	 * @return the serviceRequestReasonId
	 */
	public String getServiceRequestReasonId() {
		return ServiceRequestReasonId;
	}

	/**
	 * @param serviceRequestReasonId
	 *            the serviceRequestReasonId to set
	 */
	public void setServiceRequestReasonId(String serviceRequestReasonId) {
		ServiceRequestReasonId = serviceRequestReasonId;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return Message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		Message = message;
	}

	/**
	 * @return the header
	 */
	public String getHeader() {
		return Header;
	}

	/**
	 * @param header
	 *            the header to set
	 */
	public void setHeader(String header) {
		Header = header;
	}

	/**
	 * @return the isUserMessage
	 */
	public boolean getIsUserMessage() {
		return IsUserMessage;
	}

	/**
	 * @param isUserMessage
	 *            the isUserMessage to set
	 */
	public void setIsUserMessage(boolean isUserMessage) {
		IsUserMessage = isUserMessage;
	}

	/**
	 * @return the targetUserType
	 */
	public String getTargetUserType() {
		return TargetUserType;
	}

	/**
	 * @param targetUserType
	 *            the targetUserType to set
	 */
	public void setTargetUserType(String targetUserType) {
		TargetUserType = targetUserType;
	}
}
