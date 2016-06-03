package com.schoolteacher.pojos;

import java.io.Serializable;

public class CallToActionAppointment implements Serializable {
	private static final long serialVersionUID = 517233425407956023L;
	private int Id;
	private String SlotId;
	private String SessionId;
	private String Time;
	private String Date;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getSlotId() {
		return SlotId;
	}

	public void setSlotId(String slotId) {
		SlotId = slotId;
	}

	public String getSessionId() {
		return SessionId;
	}

	public void setSessionId(String sessionId) {
		SessionId = sessionId;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}
}
