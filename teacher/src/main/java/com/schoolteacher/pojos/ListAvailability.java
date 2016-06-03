package com.schoolteacher.pojos;

import java.io.Serializable;
import java.util.List;

public class ListAvailability implements Serializable {
	/**
	 * @return the day
	 */
	public String getDay() {
		return Day;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		Day = day;
	}
	/**
	 * @return the isDayAvailable
	 */
	public boolean isIsDayAvailable() {
		return IsDayAvailable;
	}
	/**
	 * @param isDayAvailable the isDayAvailable to set
	 */
	public void setIsDayAvailable(boolean isDayAvailable) {
		IsDayAvailable = isDayAvailable;
	}
	/**
	 * @return the sessions
	 */
	public List<Session> getSessions() {
		return Sessions;
	}
	/**
	 * @param sessions the sessions to set
	 */
	public void setSessions(List<Session> sessions) {
		Sessions = sessions;
	}
	private String Day;
	private boolean IsDayAvailable;
	private List<Session> Sessions;
}
