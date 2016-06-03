package com.schoolteacher.pojos;

import java.io.Serializable;

public class JeevSearchAvailability implements Serializable {

	private static final long serialVersionUID = 3252213146678437399L;
	private boolean Monday;
	private boolean Tuesday;
	private boolean Wednesday;
	private boolean Thrusday;
	private boolean Friday;
	private boolean Saturday;
	private boolean Sunday;

	public boolean isMonday() {
		return Monday;
	}

	public void setMonday(boolean monday) {
		this.Monday = monday;
	}

	public boolean isTuesday() {
		return Tuesday;
	}

	public void setTuesday(boolean tuesday) {
		this.Tuesday = tuesday;
	}

	public boolean isWednesday() {
		return Wednesday;
	}

	public void setWednesday(boolean wednesday) {
		this.Wednesday = wednesday;
	}

	public boolean isThrusday() {
		return Thrusday;
	}

	public void setThrusday(boolean thrusday) {
		this.Thrusday = thrusday;
	}

	public boolean isFriday() {
		return Friday;
	}

	public void setFriday(boolean friday) {
		this.Friday = friday;
	}

	public boolean isSaturday() {
		return Saturday;
	}

	public void setSaturday(boolean saturday) {
		this.Saturday = saturday;
	}

	public boolean isSunday() {
		return Sunday;
	}

	public void setSunday(boolean sunday) {
		this.Sunday = sunday;
	}
}
