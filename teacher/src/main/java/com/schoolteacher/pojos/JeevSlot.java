package com.schoolteacher.pojos;

public class JeevSlot {
	private int Id;
	private String Name;
	private String FromTime;
	private String FromTimeText;
	private String ToTime;
	private String ToTimeText;
	private boolean IsSlotAvailable;
	private int AppointmentsAvailableCount;
	private int AppointmentsPendingCount;
	private int AppointmentsConfirmedCount;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getFromTime() {
		return FromTime;
	}

	public void setFromTime(String fromTime) {
		FromTime = fromTime;
	}

	public String getFromTimeText() {
		return FromTimeText;
	}

	public void setFromTimeText(String fromTimeText) {
		FromTimeText = fromTimeText;
	}

	public String getToTime() {
		return ToTime;
	}

	public void setToTime(String toTime) {
		ToTime = toTime;
	}

	public String getToTimeText() {
		return ToTimeText;
	}

	public void setToTimeText(String toTimeText) {
		ToTimeText = toTimeText;
	}

	public boolean isIsSlotAvailable() {
		return IsSlotAvailable;
	}

	public void setIsSlotAvailable(boolean isSlotAvailable) {
		IsSlotAvailable = isSlotAvailable;
	}

	public int getAppointmentsAvailableCount() {
		return AppointmentsAvailableCount;
	}

	public void setAppointmentsAvailableCount(int appointmentsAvailableCount) {
		AppointmentsAvailableCount = appointmentsAvailableCount;
	}

	public int getAppointmentsPendingCount() {
		return AppointmentsPendingCount;
	}

	public void setAppointmentsPendingCount(int appointmentsPendingCount) {
		AppointmentsPendingCount = appointmentsPendingCount;
	}

	public int getAppointmentsConfirmedCount() {
		return AppointmentsConfirmedCount;
	}

	public void setAppointmentsConfirmedCount(int appointmentsConfirmedCount) {
		AppointmentsConfirmedCount = appointmentsConfirmedCount;
	}

}
