package com.schoolteacher.mylibrary.model;

import java.util.List;

public class Token {
	private List<EmailAndTokenTypeDictionary> EmailAndTokenTypeDictionary;

	public List<EmailAndTokenTypeDictionary> getEmailAndTokenType() {
		return EmailAndTokenTypeDictionary;
	}

	public void setEmailAndTokenType(List<EmailAndTokenTypeDictionary> emailAndTokenType) {
		this.EmailAndTokenTypeDictionary = emailAndTokenType;
	}

}
