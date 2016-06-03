package com.schoolteacher.pojos;

public class RecentTransactionHistory {
    public int Id;
    public int Status;
    public int MemberId;
    public float Amount;
    public float AmountAfterPromotion;
    public int CashbackAmount;
    public String ExternalPaymentTransactionId;
    public int PromotionId;
    public int PackageId;
    public int UpdateBy;
    public String UpdatedOnUtc;
    public int ServiceRequestId;
    public int PaymentSourceId;
    public String TransactionType;
    public ServiceRequisitionDetails SRDetails;
    public PromotionDetails PromotionDetails;
    public PackageDetails PackageDetails;


    public int getUpdateBy() {
        return UpdateBy;
    }

    public void setUpdateBy(int updateBy) {
        UpdateBy = updateBy;
    }

    public int getPaymentSourceId() {
        return PaymentSourceId;
    }

    public void setPaymentSourceId(int paymentSourceId) {
        PaymentSourceId = paymentSourceId;
    }


    public int getCashbackAmount() {
        return CashbackAmount;
    }

    public void setCashbackAmount(int cashbackAmount) {
        CashbackAmount = cashbackAmount;
    }

    public String getExternalPaymentTransactionId() {
        return ExternalPaymentTransactionId;
    }

    public void setExternalPaymentTransactionId(String externalPaymentTransactionId) {
        ExternalPaymentTransactionId = externalPaymentTransactionId;
    }

    public int getPromotionId() {
        return PromotionId;
    }

    public void setPromotionId(int promotionId) {
        PromotionId = promotionId;
    }

    public String getTransactionType() {
        return TransactionType;
    }

    public void setTransactionType(String transactionType) {
        TransactionType = transactionType;
    }

    public ServiceRequisitionDetails getSRDetails() {
        return SRDetails;
    }

    public void setSRDetails(ServiceRequisitionDetails SRDetails) {
        this.SRDetails = SRDetails;
    }

    public com.schoolteacher.pojos.PackageDetails getPackageDetails() {
        return PackageDetails;
    }

    public void setPackageDetails(com.schoolteacher.pojos.PackageDetails packageDetails) {
        PackageDetails = packageDetails;
    }

    public com.schoolteacher.pojos.PromotionDetails getPromotionDetails() {
        return PromotionDetails;
    }

    public void setPromotionDetails(com.schoolteacher.pojos.PromotionDetails promotionDetails) {
        PromotionDetails = promotionDetails;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getMemberId() {
        return MemberId;
    }

    public void setMemberId(int memberId) {
        MemberId = memberId;
    }


    public int getPackageId() {
        return PackageId;
    }

    public void setPackageId(int packageId) {
        PackageId = packageId;
    }


    public String getUpdatedOnUtc() {
        return UpdatedOnUtc;
    }

    public void setUpdatedOnUtc(String updatedOnUtc) {
        UpdatedOnUtc = updatedOnUtc;
    }

    public int getServiceRequestId() {
        return ServiceRequestId;
    }

    public void setServiceRequestId(int serviceRequestId) {
        ServiceRequestId = serviceRequestId;
    }

    public float getAmount() {
        return Amount;
    }

    public void setAmount(float amount) {
        Amount = amount;
    }

    public float getAmountAfterPromotion() {
        return AmountAfterPromotion;
    }

    public void setAmountAfterPromotion(float amountAfterPromotion) {
        AmountAfterPromotion = amountAfterPromotion;
    }

}
