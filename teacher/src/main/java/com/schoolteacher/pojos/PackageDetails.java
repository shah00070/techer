package com.schoolteacher.pojos;

import java.io.Serializable;

public class PackageDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1138158046433847896L;
	private int Id;
	private String PackageName;
	private String PackageCode;
	private String SubscriptionType;
	private int PackagePrice;
	private String Description;
	private int MaxAllowedQuantity;
	private boolean IsActive;
	private int TransactionId;

	/**
	 * @return the id
	 */
	public int getId() {
		return Id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		Id = id;
	}

	/**
	 * @return the packageName
	 */
	public String getPackageName() {
		return PackageName;
	}

	/**
	 * @param packageName
	 *            the packageName to set
	 */
	public void setPackageName(String packageName) {
		PackageName = packageName;
	}

	/**
	 * @return the packageCode
	 */
	public String getPackageCode() {
		return PackageCode;
	}

	/**
	 * @param packageCode
	 *            the packageCode to set
	 */
	public void setPackageCode(String packageCode) {
		PackageCode = packageCode;
	}

	/**
	 * @return the subscriptionType
	 */
	public String getSubscriptionType() {
		return SubscriptionType;
	}

	/**
	 * @param subscriptionType
	 *            the subscriptionType to set
	 */
	public void setSubscriptionType(String subscriptionType) {
		SubscriptionType = subscriptionType;
	}

	/**
	 * @return the packagePrice
	 */
	public int getPackagePrice() {
		return PackagePrice;
	}

	/**
	 * @param packagePrice
	 *            the packagePrice to set
	 */
	public void setPackagePrice(int packagePrice) {
		PackagePrice = packagePrice;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return Description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		Description = description;
	}

	/**
	 * @return the maxAllowedQuantity
	 */
	public int getMaxAllowedQuantity() {
		return MaxAllowedQuantity;
	}

	/**
	 * @param maxAllowedQuantity
	 *            the maxAllowedQuantity to set
	 */
	public void setMaxAllowedQuantity(int maxAllowedQuantity) {
		MaxAllowedQuantity = maxAllowedQuantity;
	}

	/**
	 * @return the isActive
	 */
	public boolean isIsActive() {
		return IsActive;
	}

	/**
	 * @param isActive
	 *            the isActive to set
	 */
	public void setIsActive(boolean isActive) {
		IsActive = isActive;
	}

	/**
	 * @return the transactionId
	 */
	public int getTransactionId() {
		return TransactionId;
	}

	/**
	 * @param transactionId
	 *            the transactionId to set
	 */
	public void setTransactionId(int transactionId) {
		TransactionId = transactionId;
	}

}
