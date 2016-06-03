package com.schoolteacher.pojos;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 9/14/2015.
 */
public class Measurement implements Serializable {
    public int Id ;
    public int IndicatorId;
    public int MemberId ;
    public List<Element2> Elements;
    //public object Comments ;
    //public List<object> AllComments ;
    public String MeasuredOn ;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIndicatorId() {
        return IndicatorId;
    }

    public void setIndicatorId(int indicatorId) {
        IndicatorId = indicatorId;
    }

    public int getMemberId() {
        return MemberId;
    }

    public void setMemberId(int memberId) {
        MemberId = memberId;
    }

    public List<Element2> getElements() {
        return Elements;
    }

    public void setElements(List<Element2> elements) {
        Elements = elements;
    }

    public String getMeasuredOn() {
        return MeasuredOn;
    }

    public void setMeasuredOn(String measuredOn) {
        MeasuredOn = measuredOn;
    }
}
