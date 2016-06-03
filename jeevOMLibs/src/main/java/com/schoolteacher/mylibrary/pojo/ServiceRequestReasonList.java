
package com.schoolteacher.mylibrary.pojo;
public class ServiceRequestReasonList {
	    private int Id;
	    private String Name;
	    private String Description;
	    private int CreatedBy;
	    private String CreatedOnUTC;
	    private int ServiceRequestTypeId;
	    private int UpdatedBy;
	private String UpdatedOnUTC;

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

	public int getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(int createdBy) {
		CreatedBy = createdBy;
	}

	public String getCreatedOnUTC() {
		return CreatedOnUTC;
	}

	public void setCreatedOnUTC(String createdOnUTC) {
		CreatedOnUTC = createdOnUTC;
	}

	public int getServiceRequestTypeId() {
		return ServiceRequestTypeId;
	}

	public void setServiceRequestTypeId(int serviceRequestTypeId) {
		ServiceRequestTypeId = serviceRequestTypeId;
	}

	public int getUpdatedBy() {
		return UpdatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		UpdatedBy = updatedBy;
	}

	public String getUpdatedOnUTC() {
		return UpdatedOnUTC;
	}

	public void setUpdatedOnUTC(String updatedOnUTC) {
		UpdatedOnUTC = updatedOnUTC;
	}
}
