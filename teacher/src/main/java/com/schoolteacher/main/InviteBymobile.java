package com.schoolteacher.main;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Header;
import retrofit.http.POST;

/**
 * Created by Perveen akhter on 22-02-2016.
 */
public interface InviteBymobile  {
    @FormUrlEncoded
    @POST("/membership/IsPhoneExists")
    void saveSearch(@Field("EmailOrPhone") String ss, @Header("authorization") String authToken, Callback<InviterequestBymobile> callback);


}
