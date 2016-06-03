package com.schoolteacher.pojos;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 9/9/2015.
 */
public class HealthVitalsData implements Serializable {
    public int Id ;
    public String Name ;
    public String Description;
    public List<HealthIndicator> HealthIndicators;

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

    public List<HealthIndicator> getHealthIndicators() {
        return HealthIndicators;
    }

    public void setHealthIndicators(List<HealthIndicator> healthIndicators) {
        HealthIndicators = healthIndicators;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
