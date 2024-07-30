package com.example.CarRentalManagementApplication.service.admin;

import com.example.CarRentalManagementApplication.dto.CarDTO;
import com.example.CarRentalManagementApplication.entity.Car;
import com.example.CarRentalManagementApplication.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<CarDTO> getAllCars() {
        return carRepository.findAll().stream().map(Car::getCarDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteCar(Integer id) {

        carRepository.deleteById(id);
    }

    @Override
    public CarDTO getCarById(Integer id) {
        Optional<Car> optionalCar =  carRepository.findById(id);
        return optionalCar.map(Car::getCarDTO).orElse(null);
    }
}
