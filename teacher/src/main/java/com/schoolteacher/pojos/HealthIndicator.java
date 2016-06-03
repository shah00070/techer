package com.schoolteacher.pojos;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 9/9/2015.
 */
public class HealthIndicator implements Serializable {
    public int Id ;
    public String Name;
    public String Description;
    public int GroupId ;
    public List<Element> Elements ;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Element> getElements() {
        return Elements;
    }

    public void setElements(List<Element> elements) {
        Elements = elements;
    }

    public int getGroupId() {
        return GroupId;
    }

    public void setGroupId(int groupId) {
        GroupId = groupId;
    }


}
