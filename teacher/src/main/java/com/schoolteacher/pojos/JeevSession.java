package com.schoolteacher.pojos;

import java.util.List;

public class JeevSession {
	private int Id;
	private String Name;
	private String Description;
	private String From;
	private String To;
	private int Interval;
	private int AppointmentPerInterval;
	private List<Integer> Days;
	private List<JeevSlot> Slots;
	private List<Integer> FacilitIds;
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
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getFrom() {
		return From;
	}
	public void setFrom(String from) {
		From = from;
	}
	public String getTo() {
		return To;
	}
	public void setTo(String to) {
		To = to;
	}
	public int getInterval() {
		return Interval;
	}
	public void setInterval(int interval) {
		Interval = interval;
	}
	public int getAppointmentPerInterval() {
		return AppointmentPerInterval;
	}
	public void setAppointmentPerInterval(int appointmentPerInterval) {
		AppointmentPerInterval = appointmentPerInterval;
	}
	public List<Integer> getDays() {
		return Days;
	}
	public void setDays(List<Integer> days) {
		Days = days;
	}
	public List<JeevSlot> getSlots() {
		return Slots;
	}
	public void setSlots(List<JeevSlot> slots) {
		Slots = slots;
	}
	public List<Integer> getFacilitIds() {
		return FacilitIds;
	}
	public void setFacilitIds(List<Integer> facilitIds) {
		FacilitIds = facilitIds;
	}

}
