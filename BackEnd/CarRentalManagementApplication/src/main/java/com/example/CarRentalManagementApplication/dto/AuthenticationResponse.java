package com.example.CarRentalManagementApplication.dto;

import com.example.CarRentalManagementApplication.util.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;
    private UserRole userRole;
    private Integer userId;
}
