package com.realworld.realworldbackend.model.DTO;

import com.realworld.realworldbackend.model.entity.UserEntity;

public class UserDTO {

    private UserEntity userEntity;

    public UserDTO(UserEntity userEntity) {
        this.userEntity = userEntity;
        this.userEntity.setPassword(null);
        this.userEntity.setId(null);
    }

    public UserEntity getUser() {
        return userEntity;
    }

    public void setUser(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
