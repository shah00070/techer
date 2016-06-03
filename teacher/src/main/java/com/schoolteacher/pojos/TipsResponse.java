package com.schoolteacher.pojos;

/**
 * Created by chandan on 15/1/16.
 */
public class TipsResponse {

    public int Id;
    public String Text;
    public String ImageUrl;
    public String LiveOn;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getLiveOn() {
        return LiveOn;
    }

    public void setLiveOn(String liveOn) {
        LiveOn = liveOn;
    }
}
