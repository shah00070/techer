package com.schoolteacher.main;

import retrofit.Callback;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Perveen akhter on 18-02-2016.
 */
public interface Invite {

    @POST("/membership")
    void invitefnd(@Query("email") String email, @Query("cellnumber") String cellnumber, Callback<Inviterequest> callback);
}