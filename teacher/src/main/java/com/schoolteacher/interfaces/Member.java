package com.schoolteacher.interfaces;

import com.schoolteacher.pojos.BloodGroupResponse;
import com.schoolteacher.pojos.ConsumerDetailsResponse;
import com.schoolteacher.pojos.ConsumerUpdateRequestModel;
import com.schoolteacher.pojos.HealthTipsResponse;
import com.schoolteacher.pojos.ResponseApi;
import com.schoolteacher.pojos.UpdateConsumerResponse;
import com.schoolteacher.pojos.UpdateEmailPasswordRequestData;
import com.schoolteacher.pojos.UserFamilyDetailsResult;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by user on 9/3/2015.
 */
public interface Member {

    @GET("/Profile/{consumer_id}/Type/3")
//    void getConsumer(@Header("X-Jeevom-RequestLocation")String locationObject,@Header("authorization") String token,@Path("consumer_id") String consumer_id, @Header("x-jeevom-android-app-version") String version, Callback<ConsumerDetailsResponse> callback);
    void getConsumer(@Header("authorization") String token, @Path("consumer_id") String consumer_id, @Header("x-jeevom-android-app-version") String version, Callback<ConsumerDetailsResponse> callback);

    @GET("/membership/BloodGroupType/list")
//    void getBloodGroup(@Header("X-Jeevom-RequestLocation")String locationObject,@Header("authorization") String token,Callback<BloodGroupResponse> callback);
    void getBloodGroup(@Header("authorization") String token, Callback<BloodGroupResponse> callback);

    @PUT("/Profile/Update")
    void updateConsumerProfile(@Header("authorization") String token, @Body ConsumerUpdateRequestModel object, Callback<UpdateConsumerResponse> callback);

    @POST("/membership/UpdateEmailCellNumber")
    void addPhoneNumberOrEmail(@Header("authorization") String token,
                               @Body UpdateEmailPasswordRequestData object,
                               Callback<ResponseApi<String>> callback);

    @GET("/tip/list")
    void getHealthTips(Callback<HealthTipsResponse> callback);

    @GET("/profile/Member/Family/{member_id}")
    void getFamilyMemberList(@Header("authorization") String token, @Path("member_id") String member_id, Callback<UserFamilyDetailsResult> callback);

}
