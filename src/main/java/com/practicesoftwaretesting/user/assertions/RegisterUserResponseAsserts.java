package com.practicesoftwaretesting.user.assertions;

import com.practicesoftwaretesting.user.model.RegisterUserResponse;
import lombok.AllArgsConstructor;

import static org.assertj.core.api.Assertions.assertThat;

@AllArgsConstructor
public class RegisterUserResponseAsserts {

    private RegisterUserResponse registerUserResponse;

    public RegisterUserResponseAsserts firstNameIs(String expectedName) {
        assertThat(registerUserResponse.getFirstName())
                .withFailMessage(String.format("firstName should be %s but was %s",
                        expectedName,
                        registerUserResponse.getFirstName()))
                .isEqualTo(expectedName);
        return this;
    }

    public RegisterUserResponseAsserts lastNameIs(String expectedName) {
        assertThat(registerUserResponse.getLastName())
                .withFailMessage(String.format("lastName should be %s but was %s",
                        expectedName,
                        registerUserResponse.getLastName()))
                .isEqualTo(expectedName);
        return this;
    }

    public RegisterUserResponseAsserts addressIs(String expectedAddress) {
        assertThat(registerUserResponse.getAddress())
                .withFailMessage(String.format("address should be %s but was %s",
                        expectedAddress,
                        registerUserResponse.getAddress()))
                .isEqualTo(expectedAddress);
        return this;
    }

    public RegisterUserResponseAsserts cityIs(String expectedCity) {
        assertThat(registerUserResponse.getCity())
                .withFailMessage(String.format("city should be %s but was %s",
                        expectedCity,
                        registerUserResponse.getCity()))
                .isEqualTo(expectedCity);
        return this;
    }

    public RegisterUserResponseAsserts countryIs(String expectedCountry) {
        assertThat(registerUserResponse.getCountry())
                .withFailMessage(String.format("country should be %s but was %s",
                        expectedCountry,
                        registerUserResponse.getCountry()))
                .isEqualTo(expectedCountry);
        return this;
    }

    public RegisterUserResponseAsserts phoneIs(String expectedPhone) {
        assertThat(registerUserResponse.getPhone())
                .withFailMessage(String.format("phone should be %s but was %s",
                        expectedPhone,
                        registerUserResponse.getPhone()))
                .isEqualTo(expectedPhone);
        return this;
    }

    public RegisterUserResponseAsserts createdAtIsNotNull() {
        assertThat(registerUserResponse.getCreatedAt())
                .withFailMessage("createdAt should not be null")
                .isNotNull();
        return this;
    }
}