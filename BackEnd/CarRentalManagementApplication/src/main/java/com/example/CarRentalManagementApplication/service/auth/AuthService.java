package com.example.CarRentalManagementApplication.service.auth;

import com.example.CarRentalManagementApplication.dto.SignUpRequest;
import com.example.CarRentalManagementApplication.dto.UserDto;

public interface AuthService {

    UserDto createCustomer(SignUpRequest signUpRequest);

    boolean hasCustomerWithEmail(String email);
}
