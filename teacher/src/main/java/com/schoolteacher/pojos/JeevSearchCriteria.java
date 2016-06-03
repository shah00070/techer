package com.schoolteacher.pojos;

import java.io.Serializable;
import java.util.List;

public class JeevSearchCriteria implements Serializable {
    private static final long serialVersionUID = -6987162999419299090L;
    private String SearchString;
    private String Timings;
    private String GenderType;
    private JeevSearchAvailability Availability;
    private List<String> Category;
    private List<String> ServiceRequisitionTypes;

    public String getSearchString() {
        return SearchString;
    }

    public void setSearchString(String searchString) {
        SearchString = searchString;
    }

    public String getTimings() {
        return Timings;
    }

    public void setTimings(String timings) {
        Timings = timings;
    }

    public String getGenderType() {
        return GenderType;
    }

    public void setGenderType(String genderType) {
        GenderType = genderType;
    }

    public JeevSearchAvailability getAvailability() {
        return Availability;
    }

    public void setAvailability(JeevSearchAvailability availability) {
        Availability = availability;
    }

    public List<String> getCategory() {
        return Category;
    }

    public void setCategory(List<String> category) {
        Category = category;
    }

    public List<String> getServiceRequisitionTypes() {
        return ServiceRequisitionTypes;
    }

    public void setServiceRequisitionTypes(List<String> serviceRequisitionTypes) {
        ServiceRequisitionTypes = serviceRequisitionTypes;
    }
}
