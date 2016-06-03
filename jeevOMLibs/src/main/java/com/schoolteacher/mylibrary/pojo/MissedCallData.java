package com.schoolteacher.mylibrary.pojo;

public class MissedCallData {
	private String UniqueRequestId;
	private boolean IsVerified;
	private String CallToVerifyNumberAsText;
	public String MessageFromProvider;
	public int TimePendingInSecs;
	public String MobilePhoneNumber;
	public String AdditionalMessage;
	public String CallToVerifyPhoneNumberImage;

	public String getUniqueRequestId() {
		return UniqueRequestId;
	}

	public void setUniqueRequestId(String uniqueRequestId) {
		UniqueRequestId = uniqueRequestId;
	}

	public boolean isIsVerified() {
		return IsVerified;
	}

	public void setIsVerified(boolean isVerified) {
		IsVerified = isVerified;
	}

	public String getCallToVerifyNumberAsText() {
		return CallToVerifyNumberAsText;
	}

	public void setCallToVerifyNumberAsText(String callToVerifyNumberAsText) {
		CallToVerifyNumberAsText = callToVerifyNumberAsText;
	}

	public String getMessageFromProvider() {
		return MessageFromProvider;
	}

	public void setMessageFromProvider(String messageFromProvider) {
		MessageFromProvider = messageFromProvider;
	}

	public int getTimePendingInSecs() {
		return TimePendingInSecs;
	}

	public void setTimePendingInSecs(int timePendingInSecs) {
		TimePendingInSecs = timePendingInSecs;
	}

	public String getMobilePhoneNumber() {
		return MobilePhoneNumber;
	}

	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		MobilePhoneNumber = mobilePhoneNumber;
	}

	public String getAdditionalMessage() {
		return AdditionalMessage;
	}

	public void setAdditionalMessage(String additionalMessage) {
		AdditionalMessage = additionalMessage;
	}

	public String getCallToVerifyPhoneNumberImage() {
		return CallToVerifyPhoneNumberImage;
	}

	public void setCallToVerifyPhoneNumberImage(String callToVerifyPhoneNumberImage) {
		CallToVerifyPhoneNumberImage = callToVerifyPhoneNumberImage;
	}

}
