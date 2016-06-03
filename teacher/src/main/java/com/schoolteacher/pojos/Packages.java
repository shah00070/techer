package com.schoolteacher.pojos;

import java.util.List;

public class Packages {
	private int Id;
	private String PackageName;
	private String PackageCode;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getPackageName() {
		return PackageName;
	}

	public void setPackageName(String packageName) {
		PackageName = packageName;
	}

	public String getPackageCode() {
		return PackageCode;
	}

	public void setPackageCode(String packageCode) {
		PackageCode = packageCode;
	}

	public double getPackagePrice() {
		return PackagePrice;
	}

	public void setPackagePrice(double packagePrice) {
		PackagePrice = packagePrice;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public int getMaxAllowedQuantity() {
		return MaxAllowedQuantity;
	}

	public void setMaxAllowedQuantity(int maxAllowedQuantity) {
		MaxAllowedQuantity = maxAllowedQuantity;
	}

	public boolean isIsActive() {
		return IsActive;
	}

	public void setIsActive(boolean isActive) {
		IsActive = isActive;
	}

	public int getPackageType() {
		return PackageType;
	}

	public void setPackageType(int packageType) {
		PackageType = packageType;
	}

	public boolean isIsSuccess() {
		return IsSuccess;
	}

	public void setIsSuccess(boolean isSuccess) {
		IsSuccess = isSuccess;
	}

	public int getTransactionId() {
		return TransactionId;
	}

	public void setTransactionId(int transactionId) {
		TransactionId = transactionId;
	}

	public List<PkgPromotion> getPkgPromotion() {
		return PkgPromotion;
	}

	public void setPkgPromotion(List<PkgPromotion> pkgPromotion) {
		PkgPromotion = pkgPromotion;
	}

	// private object SubscriptionType ;
	private double PackagePrice;
	private String Description;
	private int MaxAllowedQuantity;
	private boolean IsActive;
	private int PackageType;

	private boolean IsSuccess;
	private int TransactionId;
	private List<PkgPromotion> PkgPromotion;
	// private object ExternalTransactionid ;
}
