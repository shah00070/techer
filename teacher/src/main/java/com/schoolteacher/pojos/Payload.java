package com.schoolteacher.pojos;

import java.util.List;

public class Payload {
	private List<EmailPayload> Messages;

	/**
	 * @return the messages
	 */
	public List<EmailPayload> getMessages() {
		return Messages;
	}

	/**
	 * @param messages the messages to set
	 */
	public void setMessages(List<EmailPayload> messages) {
		Messages = messages;
	}
}
