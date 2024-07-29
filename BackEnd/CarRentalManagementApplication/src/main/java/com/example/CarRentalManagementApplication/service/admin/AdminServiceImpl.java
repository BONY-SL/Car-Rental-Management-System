package com.example.CarRentalManagementApplication.service.admin;

import com.example.CarRentalManagementApplication.dto.CarDTO;
import com.example.CarRentalManagementApplication.entity.Car;
import com.example.CarRentalManagementApplication.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final CarRepository carRepository;

    @Override
    public boolean postCar(CarDTO carDTO) throws IOException {

        try {
            Car car =new Car();

            car.setName(carDTO.getName());
            car.setColor(carDTO.getColor());
            car.setBrand(carDTO.getBrand());
            car.setType(carDTO.getType());
            car.setPrice(carDTO.getPrice());
            car.setDescription(carDTO.getDescription());
            car.setTransmission(carDTO.getTransmission());
            car.setYear(carDTO.getYear());
            car.setImage(carDTO.getImage().getBytes());
            carRepository.save(car);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
