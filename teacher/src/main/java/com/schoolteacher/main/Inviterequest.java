package com.schoolteacher.main;

import com.schoolteacher.mylibrary.pojo.Status;

import java.io.Serializable;

/**
 * Created by Perveen akhter on 18-02-2016.
 */
public class Inviterequest implements Serializable {

    private Status Status;
    private String Data;

    public Status getStatus() {
        return Status;
    }

    public void setStatus(Status status) {
        Status = status;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }
}