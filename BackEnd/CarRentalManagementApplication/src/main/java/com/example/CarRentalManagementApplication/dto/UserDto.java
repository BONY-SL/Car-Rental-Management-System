package com.example.CarRentalManagementApplication.dto;

import com.example.CarRentalManagementApplication.util.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer id;

    private String name;

    private String email;

    private String password;

    private UserRole userRole;
}
