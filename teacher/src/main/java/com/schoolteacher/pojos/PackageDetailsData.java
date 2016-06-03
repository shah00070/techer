package com.schoolteacher.pojos;

import java.util.List;

/**
 * Created by chandan on 31/12/15.
 */
public class PackageDetailsData {

    public long Id;
    public String PackageName;
    public String PackageCode;
    public String SubscriptionType;
    public int PackagePrice;
    public String Description;
    public int MaxAllowedQuantity;
    public Boolean IsActive;
    public int PackageType;
    public String UpdatedBy;
    public String CreatedBy;
    public String UpdatedOnUTC;
    public String CreatedOnUTC;
    public Boolean IsSuccess;
    public long TransactionId;
    public List<PromotionData> PkgPromotion;
    public String ExternalTransactionid;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
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

    public String getSubscriptionType() {
        return SubscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        SubscriptionType = subscriptionType;
    }

    public int getPackagePrice() {
        return PackagePrice;
    }

    public void setPackagePrice(int packagePrice) {
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

    public Boolean getIsActive() {
        return IsActive;
    }

    public void setIsActive(Boolean isActive) {
        IsActive = isActive;
    }

    public int getPackageType() {
        return PackageType;
    }

    public void setPackageType(int packageType) {
        PackageType = packageType;
    }

    public String getUpdatedBy() {
        return UpdatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        UpdatedBy = updatedBy;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getUpdatedOnUTC() {
        return UpdatedOnUTC;
    }

    public void setUpdatedOnUTC(String updatedOnUTC) {
        UpdatedOnUTC = updatedOnUTC;
    }

    public String getCreatedOnUTC() {
        return CreatedOnUTC;
    }

    public void setCreatedOnUTC(String createdOnUTC) {
        CreatedOnUTC = createdOnUTC;
    }

    public Boolean getIsSuccess() {
        return IsSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        IsSuccess = isSuccess;
    }

    public long getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(long transactionId) {
        TransactionId = transactionId;
    }

    public List<PromotionData> getPkgPromotion() {
        return PkgPromotion;
    }

    public void setPkgPromotion(List<PromotionData> pkgPromotion) {
        PkgPromotion = pkgPromotion;
    }

    public String getExternalTransactionid() {
        return ExternalTransactionid;
    }

    public void setExternalTransactionid(String externalTransactionid) {
        ExternalTransactionid = externalTransactionid;
    }
}
