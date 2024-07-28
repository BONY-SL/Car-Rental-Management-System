package com.example.CarRentalManagementApplication.entity;

import com.example.CarRentalManagementApplication.util.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    private String password;

    private UserRole userRole;


}
