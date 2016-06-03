package com.schoolteacher.pojos;

import java.io.Serializable;
import java.util.List;

public class Session implements Serializable {
	private int SessionId;
	private int FacilityId;
	private String SessionName;
	private int SessionDay;
	private String SessionFrom;
	private String SessionTo;
	private boolean IsSessionAvailable;

	/**
	 * @return the sessionId
	 */
	public int getSessionId() {
		return SessionId;
	}

	/**
	 * @param sessionId
	 *            the sessionId to set
	 */
	public void setSessionId(int sessionId) {
		SessionId = sessionId;
	}

	/**
	 * @return the facilityId
	 */
	public int getFacilityId() {
		return FacilityId;
	}

	/**
	 * @param facilityId
	 *            the facilityId to set
	 */
	public void setFacilityId(int facilityId) {
		FacilityId = facilityId;
	}

	/**
	 * @return the sessionName
	 */
	public String getSessionName() {
		return SessionName;
	}

	/**
	 * @param sessionName
	 *            the sessionName to set
	 */
	public void setSessionName(String sessionName) {
		SessionName = sessionName;
	}

	/**
	 * @return the sessionDay
	 */
	public int getSessionDay() {
		return SessionDay;
	}

	/**
	 * @param sessionDay
	 *            the sessionDay to set
	 */
	public void setSessionDay(int sessionDay) {
		SessionDay = sessionDay;
	}

	/**
	 * @return the sessionFrom
	 */
	public String getSessionFrom() {
		return SessionFrom;
	}

	/**
	 * @param sessionFrom
	 *            the sessionFrom to set
	 */
	public void setSessionFrom(String sessionFrom) {
		SessionFrom = sessionFrom;
	}

	/**
	 * @return the sessionTo
	 */
	public String getSessionTo() {
		return SessionTo;
	}

	/**
	 * @param sessionTo
	 *            the sessionTo to set
	 */
	public void setSessionTo(String sessionTo) {
		SessionTo = sessionTo;
	}

	/**
	 * @return the isSessionAvailable
	 */
	public boolean isIsSessionAvailable() {
		return IsSessionAvailable;
	}

	/**
	 * @param isSessionAvailable
	 *            the isSessionAvailable to set
	 */
	public void setIsSessionAvailable(boolean isSessionAvailable) {
		IsSessionAvailable = isSessionAvailable;
	}

	/**
	 * @return the slots
	 */
	public List<Slot> getSlots() {
		return Slots;
	}

	/**
	 * @param slots
	 *            the slots to set
	 */
	public void setSlots(List<Slot> slots) {
		Slots = slots;
	}

	/**
	 * @return the modes
	 */
	public List<Mode> getModes() {
		return Modes;
	}

	/**
	 * @param modes
	 *            the modes to set
	 */
	public void setModes(List<Mode> modes) {
		Modes = modes;
	}

	private List<Slot> Slots;
	private List<Mode> Modes;
}
