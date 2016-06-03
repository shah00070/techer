package com.schoolteacher.pojos;

import java.io.Serializable;

/**
 * Created by user on 9/9/2015.
 */
public class MeasurementUnit implements Serializable {

    public int Id;
    public String Name;
    public String Description;
    public boolean IsDefault;

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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public boolean isDefault() {
        return IsDefault;
    }

    public void setIsDefault(boolean isDefault) {
        IsDefault = isDefault;
    }



}
