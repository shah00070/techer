package com.schoolteacher.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class JeevAddress implements Serializable, Parcelable {
	public JeevAddress(String line1, String line2, String landmark,
			String city, String state, String country, String area,
			String zipCode, double latitude, double longitude) {
		super();
		Line1 = line1;
		Line2 = line2;
		Landmark = landmark;
		City = city;
		State = state;
		Country = country;
		Area = area;
		ZipCode = zipCode;
		Latitude = latitude;
		Longitude = longitude;
	}

	private static final long serialVersionUID = 8364980171429707198L;
	private String Line1;
	private String Line2;
	private String Landmark;
	private String City;
	private String State;
	private String Country;
	private String Area;
	private String ZipCode;
	private double Latitude;
	private double Longitude;

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Storing the data to Parcel object
	 **/
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(Line1);
		dest.writeString(Line2);
		dest.writeString(Landmark);
		dest.writeString(City);
		dest.writeString(State);
		dest.writeString(Country);
		dest.writeString(Area);
		dest.writeString(ZipCode);
		dest.writeDouble(Latitude);
		dest.writeDouble(Longitude);

	}

	/**
	 * Retrieving data from Parcel object This constructor is invoked by the
	 * method createFromParcel(Parcel source) of the object CREATOR
	 **/
	private JeevAddress(Parcel in) {
		this.Line1 = in.readString();
		this.Line2 = in.readString();
		this.Landmark = in.readString();
		this.City = in.readString();
		this.State = in.readString();
		this.State = in.readString();
		this.Country = in.readString();
		this.Area = in.readString();
		this.ZipCode = in.readString();
		this.Latitude = in.readDouble();
		this.Longitude = in.readDouble();
	}

	public static final Creator<JeevAddress> CREATOR = new Creator<JeevAddress>() {

		@Override
		public JeevAddress createFromParcel(Parcel source) {
			return new JeevAddress(source);
		}

		@Override
		public JeevAddress[] newArray(int size) {
			return new JeevAddress[size];
		}
	};

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
