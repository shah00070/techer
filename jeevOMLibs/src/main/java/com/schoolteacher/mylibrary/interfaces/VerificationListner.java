package com.schoolteacher.mylibrary.interfaces;

public interface VerificationListner {
	void onEmailVerification(String result);

	void onMissedCallVerification(String result);

	void onCodeRegenerate(String result);

	void onAddAndLoadUser(String result);
	
	void onPhoneCodeRegenerate(String result);

}