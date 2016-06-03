package com.schoolteacher.pojos;

import java.io.Serializable;

public class OrderAddress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1460229057530465800L;
	private String AddressLine1;

	public String getAddressLine1() {
		return AddressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		AddressLine1 = addressLine1;
	}
}
