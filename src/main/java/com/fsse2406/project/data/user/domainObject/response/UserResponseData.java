package com.fsse2406.project.data.user.domainObject.response;

import com.fsse2406.project.data.user.entity.UserEntity;

public class UserResponseData {
    private Integer uid;
    private String firebaseUid;
    private String email;

    public UserResponseData(UserEntity entity) {
        this.uid = entity.getUid();
        this.firebaseUid = entity.getFirebaseUid();
        this.email = entity.getEmail();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
