package com.schoolteacher.mylibrary.pojo;

public class ChangePasswordAuthenticateRequest {
    private int UserId;
    String OldPassword;
    String NewPassword;
    private String ChangeDate;
    private String ChangeTime;


    public String getChangeTime() {
        return ChangeTime;
    }

    public void setChangeTime(String changeTime) {
        ChangeTime = changeTime;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        this.UserId = userId;
    }

    public String getOldPassword() {
        return OldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.OldPassword = oldPassword;
    }

    public String getNewPassword() {
        return NewPassword;
    }

    public void setNewPassword(String newPassword) {
        this.NewPassword = newPassword;
    }

    public String getChangeDate() {
        return ChangeDate;
    }

    public void setChangeDate(String changeDate) {
        ChangeDate = changeDate;
    }

}
