package com.realworld.realworldbackend.model.DTO;

import com.realworld.realworldbackend.model.User;

public class UserDTO {

    private User user;

    public UserDTO(User user) {
        this.user = user;
        this.user.setPassword(null);
        this.user.setId(null);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
