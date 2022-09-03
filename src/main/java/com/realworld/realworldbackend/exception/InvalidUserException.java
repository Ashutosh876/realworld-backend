package com.realworld.realworldbackend.exception;

public class InvalidUserException extends Exception {

    private String msg;

    public InvalidUserException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
