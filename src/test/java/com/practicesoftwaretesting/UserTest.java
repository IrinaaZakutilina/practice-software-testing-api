package com.practicesoftwaretesting;

import com.github.javafaker.Faker;
import com.practicesoftwaretesting.user.UserController;
import com.practicesoftwaretesting.model.LoginRequest;
import com.practicesoftwaretesting.model.LoginResponse;
import com.practicesoftwaretesting.model.RegisterUserRequest;
import com.practicesoftwaretesting.model.RegisterUserResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
public class UserTest extends BaseTest {

    private static final String USER_PASSWORD = "12Example#";
    private String userEmail;

    UserController userController = new UserController();

    @Test
    void testUser() {
        userEmail = getUserEmail();
        // Register user
        var registerUserRequest = buildUser();
        var registerUserResponse = userController.registerUser(registerUserRequest)
                .as(RegisterUserResponse.class);
        assertNotNull(registerUserResponse.getId());
        // Login user
        var loginRequestBody = new LoginRequest(userEmail, USER_PASSWORD);
        var userLoginResponse = userController.loginUser(loginRequestBody)
                .as(LoginResponse.class);
        assertNotNull(userLoginResponse.getAccessToken());
        // Login as admin
        var adminLoginRequestBody = new LoginRequest("admin@practicesoftwaretesting.com", "welcome01");
        var adminloginResponse = userController.loginUser(adminLoginRequestBody)
                .as(LoginResponse.class);
        // Delete user
        var userId = registerUserResponse.getId();
        var token = adminloginResponse.getAccessToken();
        userController.deleteUser(userId, token)
                .then()
                .statusCode(204);
    }
    private RegisterUserRequest buildUser() {
        return RegisterUserRequest.builder()
                .firstName("Jake")
                .lastName("Sparrow")
                .address("Street 1")
                .city("City")
                .state("State")
                .country("Country")
                .postcode("1234AA")
                .phone("0987654532")
                .dob("1941-01-01")
                .password(USER_PASSWORD)
                .email(userEmail)
                .build();
    }
    private String getUserEmail() {
        return Faker.instance()
                .friends()
                .character()
                .toLowerCase()
                .replaceAll(" ", "") + "@gmail.com";
    }
}