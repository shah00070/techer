package com.schoolteacher.pojos;

public class ServiceRequisitionActionData {

	private int ActionId;
	private String ChangeType;
	private String ChangedBy;
	private int ChangedByUserType;
	private String DocumentIds;
	private int FromStatus;
	private String Message;
	private String ServiceConfigurationCode;
	private String ServiceRequisitionPublicId;

	public int getActionId() {
		return ActionId;
	}

	public void setActionId(int actionId) {
		ActionId = actionId;
	}

	public String getChangeType() {
		return ChangeType;
	}

	public void setChangeType(String changeType) {
		ChangeType = changeType;
	}

	public String getChangedBy() {
		return ChangedBy;
	}

	public void setChangedBy(String changedBy) {
		ChangedBy = changedBy;
	}

	public int getChangedByUserType() {
		return ChangedByUserType;
	}

	public void setChangedByUserType(int changedByUserType) {
		ChangedByUserType = changedByUserType;
	}

	public String getDocumentIds() {
		return DocumentIds;
	}

	public void setDocumentIds(String documentIds) {
		DocumentIds = documentIds;
	}

	public int getFromStatus() {
		return FromStatus;
	}

	public void setFromStatus(int fromStatus) {
		FromStatus = fromStatus;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getServiceConfigurationCode() {
		return ServiceConfigurationCode;
	}

	public void setServiceConfigurationCode(String serviceConfigurationCode) {
		ServiceConfigurationCode = serviceConfigurationCode;
	}

	public String getServiceRequisitionPublicId() {
		return ServiceRequisitionPublicId;
	}

	public void setServiceRequisitionPublicId(String serviceRequisitionPublicId) {
		ServiceRequisitionPublicId = serviceRequisitionPublicId;
	}
}
