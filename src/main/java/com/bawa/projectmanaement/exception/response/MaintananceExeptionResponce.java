package com.bawa.projectmanaement.exception.response;

public class MaintananceExeptionResponce {
    private String message;

    public MaintananceExeptionResponce(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}