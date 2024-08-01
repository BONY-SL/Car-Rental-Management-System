package com.example.CarRentalManagementApplication.repository;

import com.example.CarRentalManagementApplication.entity.BookedCar;
import com.example.CarRentalManagementApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookCarRepository extends JpaRepository<BookedCar,Integer> {


    List<BookedCar> findAllByUser(User userId);
}
