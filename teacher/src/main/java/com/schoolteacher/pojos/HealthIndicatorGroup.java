package com.schoolteacher.pojos;

/**
 * Created by user on 9/14/2015.
 */
public class HealthIndicatorGroup {
    public int Id ;
    public String Name ;
    public String Description ;
   // public object HealthIndicators ;

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
}
