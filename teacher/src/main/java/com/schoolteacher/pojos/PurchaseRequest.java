package com.schoolteacher.pojos;

import java.util.List;

public class PurchaseRequest {
	private String TimePreference;
	private String GeneralHealthCareRequirements;
	private List<Category> Categories;
	private List<PurchaseItems> Items;
	private Address DeliveryAddress;
	
	public String getTimePreference() {
		return TimePreference;
	}

	public void setTimePreference(String timePreference) {
		TimePreference = timePreference;
	}

	public String getGeneralHealthCareRequirements() {
		return GeneralHealthCareRequirements;
	}

	public void setGeneralHealthCareRequirements(
			String generalHealthCareRequirements) {
		GeneralHealthCareRequirements = generalHealthCareRequirements;
	}

	public List<Category> getCategories() {
		return Categories;
	}

	public void setCategories(List<Category> categories) {
		Categories = categories;
	}

	public List<PurchaseItems> getItems() {
		return Items;
	}

	public void setItems(List<PurchaseItems> items) {
		Items = items;
	}

	public Address getDeliveryAddress() {
		return DeliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		DeliveryAddress = deliveryAddress;
	}

	
}
