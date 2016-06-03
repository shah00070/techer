package com.schoolteacher.pojos;

import com.schoolteacher.mylibrary.pojo.Status;

/**
 * Created by chandan on 29/1/16.
 */
public class UserFamilyDetailsResult {

    private com.schoolteacher.mylibrary.pojo.Status Status;
    private MemberDetailsList Data;

    public MemberDetailsList getData() {
        return Data;
    }

    public void setData(MemberDetailsList data) {
        Data = data;
    }

    public Status getStatus() {
        return Status;
    }

    public void setStatus(Status status) {
        Status = status;
    }

}
