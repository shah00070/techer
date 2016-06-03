package com.schoolteacher.pojos;

import java.io.Serializable;

public class JeevLinkedResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6966762634328480543L;
	private String Name;
	private double Distance;
	private int Id;
	private String PublicId;
	private JeevContactInfo ContactInfo;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public double getDistance() {
		return Distance;
	}

	public void setDistance(double distance) {
		Distance = distance;
	}

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

	public JeevContactInfo getContactInfo() {
		return ContactInfo;
	}

	public void setContactInfo(JeevContactInfo contactInfo) {
		ContactInfo = contactInfo;
	}

}
