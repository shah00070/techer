package com.schoolteacher.pojos;

public class ConsumerContactInfo {
	private String Id;
	private String AddressLine1;
	private String Area;
	private String City;
	private String State;
	private String Country;
	private String ZipCode;
	private String Longitude;
	private String Latitude;

//	@Override
//	public String toString() {
//		return "{" + "Id" + ":" + getId() + "AddressLine1" + ":" + getAddressLine1() + "Area" + ":" + getArea() + "City" + ":" + getCity() + "State" + ":" + getState() + "Country" + ":"
//				+ getCountry() + "ZipCode" + ":" + getZipCode() + "Longitude" + ":" + getLongitude() + "Latitude" + ":" + getLatitude() + "}";
//	}

	/**
	 * @return the id
	 */
	public String getId() {
		return Id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		Id = id;
	}

	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return AddressLine1;
	}

	/**
	 * @param addressLine1
	 *            the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		AddressLine1 = addressLine1;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return Area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(String area) {
		Area = area;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return City;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		City = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return State;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		State = state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return Country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		Country = country;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return ZipCode;
	}

	/**
	 * @param zipCode
	 *            the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		ZipCode = zipCode;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return Longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return Latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

}
