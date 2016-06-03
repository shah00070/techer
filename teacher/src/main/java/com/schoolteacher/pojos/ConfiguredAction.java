package com.schoolteacher.pojos;

public class ConfiguredAction {
	private int Id;
	private int ServiceConfigurationStatusId;
	private int PostStatusId;
	private int ActionId;
	private int UserType;
	private boolean IsConsumerNotified;
	private boolean IsProviderNotified;
	private boolean IsSupportNotified;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getServiceConfigurationStatusId() {
		return ServiceConfigurationStatusId;
	}

	public void setServiceConfigurationStatusId(int serviceConfigurationStatusId) {
		ServiceConfigurationStatusId = serviceConfigurationStatusId;
	}

	public int getPostStatusId() {
		return PostStatusId;
	}

	public void setPostStatusId(int postStatusId) {
		PostStatusId = postStatusId;
	}

	public int getActionId() {
		return ActionId;
	}

	public void setActionId(int actionId) {
		ActionId = actionId;
	}

	public int getUserType() {
		return UserType;
	}

	public void setUserType(int userType) {
		UserType = userType;
	}

	public boolean isIsConsumerNotified() {
		return IsConsumerNotified;
	}

	public void setIsConsumerNotified(boolean isConsumerNotified) {
		IsConsumerNotified = isConsumerNotified;
	}

	public boolean isIsProviderNotified() {
		return IsProviderNotified;
	}

	public void setIsProviderNotified(boolean isProviderNotified) {
		IsProviderNotified = isProviderNotified;
	}

	public boolean isIsSupportNotified() {
		return IsSupportNotified;
	}

	public void setIsSupportNotified(boolean isSupportNotified) {
		IsSupportNotified = isSupportNotified;
	}

	public Status2 getStatus() {
		return Status;
	}

	public void setStatus(Status2 status) {
		Status = status;
	}

	public Action getAction() {
		return Action;
	}

	public void setAction(Action action) {
		Action = action;
	}

	private Status2 Status;
	private Action Action;
}
