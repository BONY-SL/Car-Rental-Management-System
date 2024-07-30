package com.example.CarRentalManagementApplication.service.admin;

import com.example.CarRentalManagementApplication.dto.CarDTO;

import java.io.IOException;
import java.util.List;

public interface AdminService {

    boolean postCar(CarDTO carDTO) throws IOException;
    List<CarDTO> getAllCars();

    void deleteCar(Integer id);

    CarDTO getCarById(Integer id);

}
