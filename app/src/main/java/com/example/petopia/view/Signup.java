package com.example.petopia.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petopia.R;
import com.example.petopia.controller.ILogController;
import com.example.petopia.controller.LoginController;

public class Signup extends AppCompatActivity implements ISignView {

    private EditText SignupEmail, SignupPassword, SignupConfirmPassword;
    private String strSignupEmail, strSignupPassword, strSignupConfirmPassword;
    private Button SignupBtn;
    private TextView goToLogin;
    ILogController signupController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signupController = new LoginController(this);


        SignupEmail = findViewById(R.id.SignUpEmailID);
        SignupPassword = findViewById(R.id.newPasswordSignUpId);
        SignupConfirmPassword = findViewById(R.id.confirmNewPasswordSignUpId);
        SignupBtn = findViewById(R.id.signupButtonID);
        goToLogin = findViewById(R.id.goToLoginPageID);

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });




        SignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strSignupEmail = SignupEmail.getText().toString().trim();
                strSignupPassword = SignupPassword.getText().toString().trim();
                strSignupConfirmPassword = SignupConfirmPassword.getText().toString().trim();

                signupController.onSignup(strSignupEmail, strSignupPassword, strSignupConfirmPassword);
            }
        });


    }

    @Override
    public void onSignupSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSignupError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}