package com.realworld.realworldbackend.model;

public class AuthRequest {

    private User user;

    public AuthRequest() {}

    public AuthRequest(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
