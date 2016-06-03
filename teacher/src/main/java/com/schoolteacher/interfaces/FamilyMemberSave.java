package com.schoolteacher.interfaces;



import com.schoolteacher.mylibrary.pojo.AddressOfUser;
import com.schoolteacher.mylibrary.pojo.Status;
import com.schoolteacher.pojos.AddFamilyMember;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.PUT;

public interface FamilyMemberSave {
	@PUT("/Profile/Update")
	void addFamilyMember(@Header("X-Jeevom-RequestLocation") AddressOfUser locationObject, @Header("authorization") String token, @Body AddFamilyMember object, Callback<Status> callback);

}
