package com.example.CarRentalManagementApplication.service.admin;

import com.example.CarRentalManagementApplication.dto.CarDTO;
import com.example.CarRentalManagementApplication.dto.CarDTOList;
import com.example.CarRentalManagementApplication.dto.GetBookingCarDTO;
import com.example.CarRentalManagementApplication.dto.SearchCarDTO;

import java.io.IOException;
import java.util.List;

public interface AdminService {

    boolean postCar(CarDTO carDTO) throws IOException;
    List<CarDTO> getAllCars();

    void deleteCar(Integer id);

    CarDTO getCarById(Integer id);

    boolean updateCarById(Integer id,CarDTO carDTO) throws IOException;

    List<GetBookingCarDTO> getBookingCarsByCustomer();

    boolean changeBookingStatus(Integer bookingId,String status);

    CarDTOList searchCar(SearchCarDTO searchCarDTO);

}
