package com.schoolteacher.pojos;

import java.util.List;

public class JeevSlotsInfo {
	private int Id;
	private String PublicId;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getPublicId() {
		return PublicId;
	}

	public void setPublicId(String publicId) {
		PublicId = publicId;
	}

	public String getFees() {
		return Fees;
	}

	public void setFees(String fees) {
		Fees = fees;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public List<JeevSession> getSessions() {
		return Sessions;
	}

	public void setSessions(List<JeevSession> sessions) {
		Sessions = sessions;
	}

	private String Fees;
	private String Name;
	private List<JeevSession> Sessions;
}
