package com.example.petopia.view;

public interface ISignView {
    void onSignupSuccess(String message, String userID);
    void onSignupError(String message);
}
