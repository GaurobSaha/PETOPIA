package com.example.petopia.model.pojo;

public class ResponseUser {
    private String message;

    public ResponseUser(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
