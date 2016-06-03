package com.schoolteacher.pojos;

import java.io.Serializable;

public class JeevFacilityContactInformation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6685020002870476537L;
	private JeevAddress Address;

	// public object FacilityName { get; set; }
	// public object FacilityDescription { get; set; }
	// public object PrimaryPhone { get; set; }
	// public object SecondaryPhone { get; set; }
	// public object TertiaryPhone { get; set; }
	// public object SecondaryEmail { get; set; }
	// public object TertiaryEmail { get; set; }
	// public object Availability { get; set; }
	// public bool IsPrimary { get; set; }
	// public object Fees { get; set; }
	// public object ServicesOffered { get; set; }

	public JeevAddress getAddress() {
		return Address;
	}

	public void setAddress(JeevAddress address) {
		Address = address;
	}
}
