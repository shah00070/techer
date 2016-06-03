
	package com.schoolteacher.pojos;

	import java.io.Serializable;

	public class ConsumerAddress implements Serializable {

		private static final long serialVersionUID = 6520790130021163271L;
		private int Id;
		private String AddressLine1;
		private String AddressLine2;
		private String Landmark;
		private String City;
		private String State;
		private String Country;
		private String Area;
		private String ZipCode;
		private double Latitude;
		private double Longitude;


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

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		/**
		 * @return the id
		 */
		public int getId() {
			return Id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(int id) {
			Id = id;
		}

		/**
		 * @return the addressLine1
		 */
		public String getAddressLine1() {
			return AddressLine1;
		}

		/**
		 * @param addressLine1 the addressLine1 to set
		 */
		public void setAddressLine1(String addressLine1) {
			AddressLine1 = addressLine1;
		}

		/**
		 * @return the addressLine2
		 */
		public String getAddressLine2() {
			return AddressLine2;
		}

		/**
		 * @param addressLine2 the addressLine2 to set
		 */
		public void setAddressLine2(String addressLine2) {
			AddressLine2 = addressLine2;
		}
	}


