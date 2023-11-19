package com.example.petopia.controller;


import com.example.petopia.model.pojo.User;
import com.example.petopia.model.pojo.ResponseUser;
import com.example.petopia.model.repository.Repository;
import com.example.petopia.view.ILogView;
import com.example.petopia.view.ISignView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginController implements ILogController {

    ILogView loginView;
    ISignView signupView;
    Repository repository;

    public LoginController(ILogView loginView) {
        this.loginView = loginView;
        this.repository = new Repository();
    }

    public LoginController(ISignView signupView) {
        this.signupView = signupView;
        this.repository = new Repository();
    }

    @Override
    public void onLogin(String email, String password) {
        User user = new User(email, password);

        repository.loginUser(user, new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                if (response.isSuccessful()) {
                    ResponseUser responseUser = response.body();
                    if (responseUser != null && "Login successful".equals(responseUser.getMessage())) {
                        loginView.onLoginSuccess("Login Successful");
                    } else {
                        loginView.onLoginError("Incorrect username or password" + responseUser.getMessage());
                    }
                } else {
                    loginView.onLoginError("HTTP Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
                loginView.onLoginError("Exception: " + t.getMessage());
            }
        });
    }

    @Override
    public void onSignup(String email, String pass1, String pass2) {
        User user = new User(email, pass1);
        int signupCode = user.isValid();

        if (signupCode == 0) {
            signupView.onSignupError("Please Enter Email");
        } else if (signupCode == 1) {
            signupView.onSignupError("Please enter a valid email");
        } else if (signupCode == 2) {
            signupView.onSignupError("Please enter a password");
        } else if (signupCode == 3) {
            signupView.onSignupError("Password should be more than 7 characters");
        } else if (!pass1.equals(pass2)) {
            signupView.onSignupError("Password must be same " + pass1 + " " + pass2);
        } else {
            repository.registerUser(user, new Callback<ResponseUser>() {
                @Override
                public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                    if (response.isSuccessful()) {
                        ResponseUser responseUser = response.body();
                        if (responseUser != null && "Successful".equals(responseUser.getMessage())) {
                            signupView.onSignupSuccess("Signup Successful");
                        } else {
                            signupView.onSignupError("Registration failed");
                        }
                    } else {
                        signupView.onSignupError("HTTP Error: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<ResponseUser> call, Throwable t) {
                    signupView.onSignupError("Exception: " + t.getMessage());
                }
            });
        }
    }
}




//public class LoginController implements ILogController {
//
//    ILogView loginView;
//    ISignView signupView;
//
//    public LoginController(ILogView loginView) {
//        this.loginView = loginView;
//    }
//
//    public LoginController(ISignView signupView) {
//        this.signupView = signupView;
//    }
//
//
//    @Override
//    public void onLogin(String email, String password) {
//        User user = new User(email, password);
//
//
//        try {
//            ApiService apiService = RetrofitClient.getApiService();
//            Call<ResponseUser> call = apiService.loginUser(user);
//            call.enqueue(new Callback<ResponseUser>() {
//                @Override
//                public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
//                    if (response.isSuccessful()) {
//                        ResponseUser responseUser = response.body();
//                        if (responseUser != null && "Login successful".equals(responseUser.getMessage())) {
//                            // Registration successful, update UI accordingly
//                            loginView.onLoginSuccess("Login Successful");
//                        } else {
//                            // Registration failed, show an error message
//                            loginView.onLoginError("Incorrect username or password"+ responseUser.getMessage());
//                        }
//                    } else {
//                        // Handling the error
//                        loginView.onLoginError("HTTP Error: " + response.code());
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseUser> call, Throwable t) {
//                    loginView.onLoginError("Exception: " + t.getMessage());
//                }
//            });
//        }catch (Exception e){
//            loginView.onLoginError("Exception: " + e.getMessage());
//        }
//    }
//
//    @Override
//    public void onSignup(String email, String pass1, String pass2) {
//        User user = new User(email, pass1);
//        int signupCode = user.isValid();
//
//        if (signupCode == 0) {
//            signupView.onSignupError("Please Enter Email");
//        } else if (signupCode == 1) {
//            signupView.onSignupError("Please enter a valid email");
//        } else if (signupCode == 2) {
//            signupView.onSignupError("Please enter a password");
//        } else if (signupCode == 3) {
//            signupView.onSignupError("Password should be more than 7 characters");
//        } else if (!pass1.equals(pass2)) {
//            signupView.onSignupError("Password must be same " + pass1 + " " + pass2);
//        }
//
//        try {
//            ApiService apiService = RetrofitClient.getApiService();
//            Call<ResponseUser> call = apiService.registerUser(user);
//            call.enqueue(new Callback<ResponseUser>() {
//                @Override
//                public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
//                    if (response.isSuccessful()) {
//                        ResponseUser responseUser = response.body();
//                        if (responseUser != null && "Successful".equals(responseUser.getMessage())) {
//                            // Registration successful, update UI accordingly
//                            signupView.onSignupSuccess("Signup Successful");
//                        } else {
//                            // Registration failed, show an error message
//                            signupView.onSignupError("Registration failed");
//                        }
//                    } else {
//                        // Handling the error
//                        signupView.onSignupError("HTTP Error: " + response.code());
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseUser> call, Throwable t) {
//                    signupView.onSignupError("Exception: " + t.getMessage());
//                }
//            });
//        }catch (Exception e){
//            signupView.onSignupError("Exception: " + e.getMessage());
//        }
//    }
//
//
//}