package com.schoolteacher.pojos;

/**
 * Created by chandan on 29/1/16.
 */
public class DealOfferPackageAttribute {

    private String Name;
    private String PromotionCode;
    private String Description;
    private int Id;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPromotionCode() {
        return PromotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        PromotionCode = promotionCode;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
