package com.practicesoftwaretesting.user;

import com.practicesoftwaretesting.common.BaseController;
import com.practicesoftwaretesting.model.LoginRequest;
import com.practicesoftwaretesting.model.RegisterUserRequest;
import io.restassured.response.Response;

public class UserController extends BaseController {

    public Response registerUser(RegisterUserRequest registerUserRequest) {
        return baseClient()
                .body(registerUserRequest)
                .post("/users/register");
    }

    public Response loginUser(LoginRequest loginRequest) {
        return baseClient()
                .body(loginRequest)
                .post("/users/login");
    }

    public Response deleteUser(String userId, String token) {
        return baseClient()
                .header("Authorization", "Bearer " + token)
                .delete("users/" + userId);
    }
}