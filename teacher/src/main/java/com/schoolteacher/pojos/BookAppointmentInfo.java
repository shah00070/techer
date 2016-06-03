package com.schoolteacher.pojos;

import java.io.Serializable;
import java.util.List;

public class BookAppointmentInfo implements Serializable {
	/**
	 * @return the isDefaultAvailability
	 */
	public boolean isIsDefaultAvailability() {
		return IsDefaultAvailability;
	}
	/**
	 * @param isDefaultAvailability the isDefaultAvailability to set
	 */
	public void setIsDefaultAvailability(boolean isDefaultAvailability) {
		IsDefaultAvailability = isDefaultAvailability;
	}
	/**
	 * @return the listAvailability
	 */
	public List<ListAvailability> getListAvailability() {
		return ListAvailability;
	}
	/**
	 * @param listAvailability the listAvailability to set
	 */
	public void setListAvailability(List<ListAvailability> listAvailability) {
		ListAvailability = listAvailability;
	}
	private boolean IsDefaultAvailability;
	private List<ListAvailability> ListAvailability;
}
