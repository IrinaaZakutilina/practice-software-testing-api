package com.practicesoftwaretesting.model;

import lombok.Data;

@Data
public class RegisterUserResponse {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String country;
    private String postcode;
    private String phone;
    private String dob;
    private String email;
    private String id;
    private String createdAt;
}