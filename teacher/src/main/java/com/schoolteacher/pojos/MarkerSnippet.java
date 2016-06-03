package com.schoolteacher.pojos;

/**
 * Created by Gaurav Gupta on 8/25/2015.
 */
public class MarkerSnippet {

    private String id;
    private String type;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String degree;
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public MarkerSnippet(String id, String type,String name,String degree,String image) {
        this.id = id;
        this.type = type;
        this.name=name;
        this.image=image;
        this.degree=degree;
    }
}
