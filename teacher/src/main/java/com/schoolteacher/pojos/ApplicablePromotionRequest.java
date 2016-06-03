package com.schoolteacher.pojos;

import java.io.Serializable;

/**
 * Created by chandan on 9/12/15.
 */
public class ApplicablePromotionRequest implements Serializable {

    int MemberId;
    String PromotionApplicationType;
    String PromotionRequestType;
    String ServiceRequisitionType;
    int TransactionAmount;

    public String getPromotionRequestType() {
        return PromotionRequestType;
    }

    public void setPromotionRequestType(String promotionRequestType) {
        PromotionRequestType = promotionRequestType;
    }

    public int getMemberId() {
        return MemberId;
    }

    public void setMemberId(int memberId) {
        MemberId = memberId;
    }

    public String getPromotionApplicationType() {
        return PromotionApplicationType;
    }

    public void setPromotionApplicationType(String promotionApplicationType) {
        PromotionApplicationType = promotionApplicationType;
    }

    public String getServiceRequisitionType() {
        return ServiceRequisitionType;
    }

    public void setServiceRequisitionType(String serviceRequisitionType) {
        ServiceRequisitionType = serviceRequisitionType;
    }

    public int getTransactionAmount() {
        return TransactionAmount;
    }

    public void setTransactionAmount(int transactionAmount) {
        TransactionAmount = transactionAmount;
    }


}
