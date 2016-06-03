package com.schoolteacher.pojos;

/**
 * Created by user on 9/24/2015.
 */
public class IndicatorReading {

    private int IndicatorElementId;

    public double getMeasuredValue() {
        return MeasuredValue;
    }

    public void setMeasuredValue(double measuredValue) {
        MeasuredValue = measuredValue;
    }

    private double MeasuredValue;
    private int MeasurementUnitId;

    public int getIndicatorElementId() {
        return IndicatorElementId;
    }

    public void setIndicatorElementId(int indicatorElementId) {
        IndicatorElementId = indicatorElementId;
    }

//    public int getMeasuredValue() {
//        return MeasuredValue;
//    }
//
//    public void setMeasuredValue(int measuredValue) {
//        MeasuredValue = measuredValue;
//    }

    public int getMeasurementUnitId() {
        return MeasurementUnitId;
    }

    public void setMeasurementUnitId(int measurementUnitId) {
        MeasurementUnitId = measurementUnitId;
    }


}
