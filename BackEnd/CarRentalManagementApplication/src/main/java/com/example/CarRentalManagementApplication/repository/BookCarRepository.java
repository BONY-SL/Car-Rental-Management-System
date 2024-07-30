package com.example.CarRentalManagementApplication.repository;

import com.example.CarRentalManagementApplication.entity.BookedCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCarRepository extends JpaRepository<BookedCar,Integer> {


}
