package com.practicesoftwaretesting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegisterUserResponse {

    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
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
    @JsonProperty("created_at")
    private String createdAt;
}
