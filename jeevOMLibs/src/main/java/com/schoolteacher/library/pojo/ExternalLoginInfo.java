package com.schoolteacher.library.pojo;

public class ExternalLoginInfo {
	/*
	 * gaurav gupta - 5/24/2015
	 */
	private String EmailOrPhone;

	public String getEmailOrPhone() {
		return EmailOrPhone;
	}

	public void setEmailOrPhone(String emailOrPhone) {
		EmailOrPhone = emailOrPhone;
	}

	public boolean isExternalProvider() {
		return isExternalProvider;
	}

	public void setExternalProvider(boolean isExternalProvider) {
		this.isExternalProvider = isExternalProvider;
	}

	private boolean isExternalProvider;
}
