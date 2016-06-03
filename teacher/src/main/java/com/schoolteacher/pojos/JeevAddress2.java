package com.schoolteacher.pojos;

import java.io.Serializable;

public class JeevAddress2 implements
		Serializable {
    private String Line1 ;
    private String Line2 ;
    private String Landmark ;
    private String City ;
    private String State ;
    private String Country ;
    private String Area ;
    private String ZipCode ;
    private double Latitude ;
	private double Longitude;

	public String getLine1() {
		return Line1;
	}

	public void setLine1(String line1) {
		Line1 = line1;
	}

	public String getLine2() {
		return Line2;
	}

	public void setLine2(String line2) {
		Line2 = line2;
	}

	public String getLandmark() {
		return Landmark;
	}

	public void setLandmark(String landmark) {
		Landmark = landmark;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getArea() {
		return Area;
	}

	public void setArea(String area) {
		Area = area;
	}

	public String getZipCode() {
		return ZipCode;
	}

	public void setZipCode(String zipCode) {
		ZipCode = zipCode;
	}

	public double getLatitude() {
		return Latitude;
	}

	public void setLatitude(double latitude) {
		Latitude = latitude;
	}

	public double getLongitude() {
		return Longitude;
	}

	public void setLongitude(double longitude) {
		Longitude = longitude;
	}

}
