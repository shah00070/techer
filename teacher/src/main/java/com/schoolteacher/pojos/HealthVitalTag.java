package com.schoolteacher.pojos;

import java.util.ArrayList;

/**
 * Created by user on 9/23/2015.
 */
public class HealthVitalTag {

    private ArrayList<String> listOfMeasurements;
    private ArrayList<Measurement> elements;

    public ArrayList<Measurement> getElements() {
        return elements;
    }

    public ArrayList<String> getListOfMeasurements() {
        return listOfMeasurements;
    }

    public String getName() {
        return name;
    }

    public HealthVitalTag(ArrayList<String> listOfMeasurements, String name,ArrayList<Measurement> elementsList) {

        this.listOfMeasurements = listOfMeasurements;
        this.name = name;
        this.elements=elementsList;
    }

    private String name;
}
