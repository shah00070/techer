package com.schoolteacher.pojos;

import java.io.Serializable;

public class Slot implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int SlotNumber;
	private String SlotTime;
	private String SlotDisplayName;
	private boolean IsSlotAvailable;

	/**
	 * @return the slotNumber
	 */
	public int getSlotNumber() {
		return SlotNumber;
	}

	/**
	 * @param slotNumber
	 *            the slotNumber to set
	 */
	public void setSlotNumber(int slotNumber) {
		SlotNumber = slotNumber;
	}

	/**
	 * @return the slotTime
	 */
	public String getSlotTime() {
		return SlotTime;
	}

	/**
	 * @param slotTime
	 *            the slotTime to set
	 */
	public void setSlotTime(String slotTime) {
		SlotTime = slotTime;
	}

	/**
	 * @return the slotDisplayName
	 */
	public String getSlotDisplayName() {
		return SlotDisplayName;
	}

	/**
	 * @param slotDisplayName
	 *            the slotDisplayName to set
	 */
	public void setSlotDisplayName(String slotDisplayName) {
		SlotDisplayName = slotDisplayName;
	}

	/**
	 * @return the isSlotAvailable
	 */
	public boolean isIsSlotAvailable() {
		return IsSlotAvailable;
	}

	/**
	 * @param isSlotAvailable
	 *            the isSlotAvailable to set
	 */
	public void setIsSlotAvailable(boolean isSlotAvailable) {
		IsSlotAvailable = isSlotAvailable;
	}
}
