package com.schoolteacher.appointment;

import java.io.Serializable;
import java.util.List;

public class AppointmentRequestData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int TotalServiceRequistionCount;
	public int TotalCurrentServiceRequistionCount;
	public List<ServiceRequisitionData> ServiceRequisitions;

	public int getTotalServiceRequistionCount() {
		return TotalServiceRequistionCount;
	}

	public void setTotalServiceRequistionCount(int totalServiceRequistionCount) {
		TotalServiceRequistionCount = totalServiceRequistionCount;
	}

	public int getTotalCurrentServiceRequistionCount() {
		return TotalCurrentServiceRequistionCount;
	}

	public void setTotalCurrentServiceRequistionCount(int totalCurrentServiceRequistionCount) {
		TotalCurrentServiceRequistionCount = totalCurrentServiceRequistionCount;
	}

	public List<ServiceRequisitionData> getServiceRequisitions() {
		return ServiceRequisitions;
	}

	public void setServiceRequisitions(List<ServiceRequisitionData> serviceRequisitions) {
		ServiceRequisitions = serviceRequisitions;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
