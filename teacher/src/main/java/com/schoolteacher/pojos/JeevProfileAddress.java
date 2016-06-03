package com.schoolteacher.pojos;

public class JeevProfileAddress extends JeevAddress {
	public int getId() {
		return Id;
	}

	public String getAddressLine1() {
		return AddressLine1;
	}

	public String getAddressLine2() {
		return AddressLine2;
	}

	private static final long serialVersionUID = 5294358436314344970L;

	public JeevProfileAddress(String line1, String line2, String landmark,
			String city, String state, String country, String area,
			String zipCode, double latitude, double longitude, int id,
			String addressLine1, String addressLine2) {
		super(line1, line2, landmark, city, state, country, area, zipCode,
				latitude, longitude);
		Id = id;
		AddressLine1 = addressLine1;
		AddressLine2 = addressLine2;
	}

	private int Id;
	private String AddressLine1;
	private String AddressLine2;
}
