package com.realworld.realworldbackend.model;

import com.realworld.realworldbackend.model.entity.UserEntity;

public class AuthRequest {

    private UserEntity userEntity;

    public AuthRequest() {}

    public AuthRequest(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public UserEntity getUser() {
        return userEntity;
    }

    public void setUser(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
