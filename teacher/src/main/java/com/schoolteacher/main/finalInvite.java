package com.schoolteacher.main;

import com.schoolteacher.mylibrary.pojo.MembershipAuthenticateResponse;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.POST;

/**
 * Created by Perveen akhter on 18-02-2016.
 */
public interface finalInvite {
   /* @Headers({
            "Content-type: application/json"
    })
    @FormUrlEncoded
    @POST("/membership/InviteMember")
    void saveSearch(

                    @Field("FirstName") String FirstName,
                    @Field("LastName") String LastName,
                    @Field("Email") String Email,
                    @Field("CellNumber") String CellNumber,
                    @Field("InvitationText") String InvitationText,
            @Field("NameOfInvitingMember") String NameOfInvitingMember,
            @Field("NameOfInviteeMember") String NameOfInviteeMember,
            @Field("InvitingMemberId") String InvitingMemberId,
                    @Field("SourceId") int SourceId,
                    @Header("authorization") String authToken,
                    Callback<finalInviterequest> callback);
*/


    @POST("/membership/InviteMember")
    void onSuccessFullMemberSignIn(@Body finalInviterequestle memAuthenticateRequest,
                                   @Header("authorization") String authToken,
                                   Callback<MembershipAuthenticateResponse> callback);




}
