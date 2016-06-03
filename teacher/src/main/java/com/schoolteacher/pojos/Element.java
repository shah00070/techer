package com.schoolteacher.pojos;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 9/9/2015.
 */
public class Element implements Serializable {
    public int Id ;
    public String Name ;
    public String Description ;
    public int IndicatorId ;
    public List<MeasurementUnit> MeasurementUnits ;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getIndicatorId() {
        return IndicatorId;
    }

    public void setIndicatorId(int indicatorId) {
        IndicatorId = indicatorId;
    }

    public List<MeasurementUnit> getMeasurementUnits() {
        return MeasurementUnits;
    }

    public void setMeasurementUnits(List<MeasurementUnit> measurementUnits) {
        MeasurementUnits = measurementUnits;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }


}
