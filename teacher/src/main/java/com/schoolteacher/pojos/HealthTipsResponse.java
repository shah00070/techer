package com.schoolteacher.pojos;

import com.schoolteacher.mylibrary.pojo.Status;

import java.util.ArrayList;

/**
 * Created by chandan on 15/1/16.
 */
public class HealthTipsResponse {

    private Status status;
    private ArrayList<TipsResponse> Data;

    public ArrayList<TipsResponse> getData() {
        return Data;
    }

    public void setData(ArrayList<TipsResponse> data) {
        Data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
