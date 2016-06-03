package com.schoolteacher.pojos;

import java.io.Serializable;

public class JeevSearchGender implements Serializable {

	private static final long serialVersionUID = 8223355160094117673L;
	private boolean both;
	private boolean male;
	private boolean female;

	public boolean isBoth() {
		return both;
	}

	public void setBoth(boolean both) {
		this.both = both;
	}

	public boolean isMale() {
		return male;
	}

	public void setMale(boolean male) {
		this.male = male;
	}

	public boolean isFemale() {
		return female;
	}

	public void setFemale(boolean female) {
		this.female = female;
	}
}
