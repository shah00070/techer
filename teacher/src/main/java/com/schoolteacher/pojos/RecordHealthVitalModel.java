package com.schoolteacher.pojos;

import java.util.ArrayList;

/**
 * Created by user on 9/24/2015.
 */
public class RecordHealthVitalModel {

    private int IndicatorId;
    private int MemberId;
    private String Comments;
    private String MeasuredOn;
    private ArrayList<IndicatorReading> Elements;

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

    public String getMeasuredOn() {
        return MeasuredOn;
    }

    public void setMeasuredOn(String measuredOn) {
        MeasuredOn = measuredOn;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    public ArrayList<IndicatorReading> getElements() {
        return Elements;
    }

    public void setElements(ArrayList<IndicatorReading> elements) {
        Elements = elements;
    }
}
