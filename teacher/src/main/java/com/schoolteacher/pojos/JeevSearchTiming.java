package com.schoolteacher.pojos;

import java.io.Serializable;

public class JeevSearchTiming implements Serializable {

	private static final long serialVersionUID = -2313536819174933423L;
	private boolean morning;
	private boolean afternoon;
	private boolean evening;

	public boolean isMorning() {
		return morning;
	}

	public void setMorning(boolean morning) {
		this.morning = morning;
	}

	public boolean isAfternoon() {
		return afternoon;
	}

	public void setAfternoon(boolean afternoon) {
		this.afternoon = afternoon;
	}

	public boolean isEvening() {
		return evening;
	}

	public void setEvening(boolean evening) {
		this.evening = evening;
	}
}
