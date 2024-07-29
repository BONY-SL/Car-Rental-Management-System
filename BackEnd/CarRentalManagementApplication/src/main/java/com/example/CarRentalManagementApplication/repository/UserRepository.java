package com.example.CarRentalManagementApplication.repository;

import com.example.CarRentalManagementApplication.entity.User;
import com.example.CarRentalManagementApplication.util.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);

    User findByUserRole(UserRole userRole);
}
