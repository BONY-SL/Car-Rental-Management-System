package com.example.CarRentalManagementApplication.service.customer;

import com.example.CarRentalManagementApplication.dto.BookCarDTO;
import com.example.CarRentalManagementApplication.dto.CarDTO;

import java.util.List;

public interface CustomerService {

    List<CarDTO> getAllCarsForCustomer();

    boolean bookCar(BookCarDTO bookCarDTO);


    CarDTO getCarById(Integer id);
}
