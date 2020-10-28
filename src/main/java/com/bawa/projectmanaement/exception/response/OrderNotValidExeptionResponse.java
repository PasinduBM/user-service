package com.bawa.projectmanaement.exception.response;

public class OrderNotValidExeptionResponse {
    private String message;

    public OrderNotValidExeptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
