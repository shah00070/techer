package com.schoolteacher.mylibrary.interfaces;

public interface SendEmailInterface {
	void onClickContinue(String name, String email, String contact);

	void onEmailSuccess(String result);

	void onClickCancel();

	void onPhoneSucess(String result);
}
