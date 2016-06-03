package com.schoolteacher.mylibrary.pojo;

import java.io.Serializable;

public class MemberContactInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Address Address ;
//    private object FacilityName ;
//    private object FacilityDescription ;
//    private object PrimaryPhone ;
//    private object SecondaryPhone ;
//    private object TertiaryPhone ;
//    private object SecondaryEmail ;
//    private object TertiaryEmail ;
//    private object Availability ;
  //  private boolean IsPrimary ;
//    private String Fees ;
//    private String ServicesOffered ;

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return Address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		Address = address;
	}
}
