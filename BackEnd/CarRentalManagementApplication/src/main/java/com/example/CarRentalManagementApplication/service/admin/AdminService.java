package com.example.CarRentalManagementApplication.service.admin;

import com.example.CarRentalManagementApplication.dto.CarDTO;

import java.io.IOException;

public interface AdminService {

    boolean postCar(CarDTO carDTO) throws IOException;
}
