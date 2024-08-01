package com.example.CarRentalManagementApplication.service.customer;

import com.example.CarRentalManagementApplication.dto.*;

import java.util.List;

public interface CustomerService {

    List<CarDTO> getAllCarsForCustomer();

    boolean bookCar(BookCarDTO bookCarDTO);


    CarDTO getCarById(Integer id);

    List<GetBookingCarDTO> getBookingsByUserId(Integer userId);

    CarDTOList searchCar(SearchCarDTO searchCarDTO);
}
