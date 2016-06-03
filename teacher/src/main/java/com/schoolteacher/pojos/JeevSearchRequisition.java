package com.schoolteacher.pojos;

import java.io.Serializable;

public class JeevSearchRequisition implements Serializable {

	private static final long serialVersionUID = -1580524774984263278L;
	private boolean email;
	private boolean video;
	private boolean chat;
	private boolean phone;
	private boolean home;
	private boolean clinic;

	public boolean isEmail() {
		return email;
	}

	public void setEmail(boolean email) {
		this.email = email;
	}

	public boolean isVideo() {
		return video;
	}

	public void setVideo(boolean video) {
		this.video = video;
	}

	public boolean isChat() {
		return chat;
	}

	public void setChat(boolean chat) {
		this.chat = chat;
	}

	public boolean isPhone() {
		return phone;
	}

	public void setPhone(boolean phone) {
		this.phone = phone;
	}

	public boolean isHome() {
		return home;
	}

	public void setHome(boolean home) {
		this.home = home;
	}

	public boolean isClinic() {
		return clinic;
	}

	public void setClinic(boolean clinic) {
		this.clinic = clinic;
	}

}
