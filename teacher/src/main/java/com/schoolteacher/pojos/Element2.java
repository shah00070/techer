package com.schoolteacher.pojos;

import java.io.Serializable;

/**
 * Created by user on 9/14/2015.
 */
public class Element2 implements Serializable {
    private int MemberId;
    private int IndicatorElementId;
    //public object IndicatorElementName ;
    private String MeasuredValue;
    private String MeasureOn;
    private int MeasurementUnitId;

    public int getMemberId() {
        return MemberId;
    }

    public void setMemberId(int memberId) {
        MemberId = memberId;
    }

    public int getIndicatorElementId() {
        return IndicatorElementId;
    }

    public void setIndicatorElementId(int indicatorElementId) {
        IndicatorElementId = indicatorElementId;
    }

    public String getMeasuredValue() {
        return MeasuredValue;
    }

    public void setMeasuredValue(String measuredValue) {
        MeasuredValue = measuredValue;
    }

    public String getMeasureOn() {
        return MeasureOn;
    }

    public void setMeasureOn(String measureOn) {
        MeasureOn = measureOn;
    }

    public int getMeasurementUnitId() {
        return MeasurementUnitId;
    }

    public void setMeasurementUnitId(int measurementUnitId) {
        MeasurementUnitId = measurementUnitId;
    }

    public String getMeasurementUnitName() {
        return MeasurementUnitName;
    }

    public void setMeasurementUnitName(String measurementUnitName) {
        MeasurementUnitName = measurementUnitName;
    }

    private String MeasurementUnitName;
    //public object Comment;

}
