package com.schoolteacher.pojos;

/**
 * Created by Chandan on 11/5/2015.
 */
public class PromotionDetails {
    public String PromotionName;
    public String PromotionCode;
    public String PromotionDescription;
    public int PromotionValueType;
    public int PromotionValue;
    public boolean PromotionIsCashBack;
    public boolean PromotionIsDiscount;
    public String ProfessionaPublicId;
    public String BusinessPublicId;

    public String getPromotionCode() {
        return PromotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        PromotionCode = promotionCode;
    }

    public String getPromotionDescription() {
        return PromotionDescription;
    }

    public void setPromotionDescription(String promotionDescription) {
        PromotionDescription = promotionDescription;
    }

    public String getPromotionName() {
        return PromotionName;
    }

    public void setPromotionName(String promotionName) {
        PromotionName = promotionName;
    }

    public int getPromotionValue() {
        return PromotionValue;
    }

    public void setPromotionValue(int promotionValue) {
        PromotionValue = promotionValue;
    }

    public int getPromotionValueType() {
        return PromotionValueType;
    }

    public void setPromotionValueType(int promotionValueType) {
        PromotionValueType = promotionValueType;
    }

    public boolean isPromotionIsDiscount() {
        return PromotionIsDiscount;
    }

    public void setPromotionIsDiscount(boolean promotionIsDiscount) {
        PromotionIsDiscount = promotionIsDiscount;
    }

    public boolean isPromotionIsCashBack() {
        return PromotionIsCashBack;
    }

    public void setPromotionIsCashBack(boolean promotionIsCashBack) {
        PromotionIsCashBack = promotionIsCashBack;
    }

    public String getProfessionaPublicId() {
        return ProfessionaPublicId;
    }

    public void setProfessionaPublicId(String professionaPublicId) {
        ProfessionaPublicId = professionaPublicId;
    }

    public String getBusinessPublicId() {
        return BusinessPublicId;
    }

    public void setBusinessPublicId(String businessPublicId) {
        BusinessPublicId = businessPublicId;
    }


}