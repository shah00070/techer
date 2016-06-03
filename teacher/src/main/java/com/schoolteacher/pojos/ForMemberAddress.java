package com.schoolteacher.pojos;

public class ForMemberAddress {
	private int Id;
	private Object AddressLine1;
	private Object AddressLine2;
	private String Line1;
	private Object Line2;
	private String Area;
	private Object Landmark;
	private String City;
	private String State;
	private String Country;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Object getAddressLine1() {
		return AddressLine1;
	}

	public void setAddressLine1(Object addressLine1) {
		AddressLine1 = addressLine1;
	}

	public Object getAddressLine2() {
		return AddressLine2;
	}

	public void setAddressLine2(Object addressLine2) {
		AddressLine2 = addressLine2;
	}

	public String getLine1() {
		return Line1;
	}

	public void setLine1(String line1) {
		Line1 = line1;
	}

	public Object getLine2() {
		return Line2;
	}

	public void setLine2(Object line2) {
		Line2 = line2;
	}

	public String getArea() {
		return Area;
	}

	public void setArea(String area) {
		Area = area;
	}

	public Object getLandmark() {
		return Landmark;
	}

	public void setLandmark(Object landmark) {
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

	public String getZipCode() {
		return ZipCode;
	}

	public void setZipCode(String zipCode) {
		ZipCode = zipCode;
	}

	public Object getLatitude() {
		return Latitude;
	}

	public void setLatitude(Object latitude) {
		Latitude = latitude;
	}

	public Object getLongitude() {
		return Longitude;
	}

	public void setLongitude(Object longitude) {
		Longitude = longitude;
	}

	private String ZipCode;
	private Object Latitude;
	private Object Longitude;
}
