package com.practicesoftwaretesting.user.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterUserRequest {

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String country;
    private String postcode;
    private String phone;
    private String dob;
    private String password;
    private String email;
}