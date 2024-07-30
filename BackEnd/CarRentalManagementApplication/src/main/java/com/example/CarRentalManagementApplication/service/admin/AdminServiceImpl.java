package com.example.CarRentalManagementApplication.service.admin;

import com.example.CarRentalManagementApplication.dto.CarDTO;
import com.example.CarRentalManagementApplication.entity.Car;
import com.example.CarRentalManagementApplication.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            Date date = new Date(carDTO.getYear());
            car.setYear(date);
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

    @Override
    public boolean updateCarById(Integer id, CarDTO carDTO) throws IOException {

        Optional<Car> optionalCar = carRepository.findById(id);


        if(optionalCar.isPresent()){
            Car exisitingCar = optionalCar.get();

            if(carDTO.getImage() != null){

                exisitingCar.setImage(carDTO.getImage().getBytes());

            }
            exisitingCar.setPrice(carDTO.getPrice());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date year = dateFormat.parse(carDTO.getYear());
                exisitingCar.setYear(year);
            } catch (ParseException e) {
                return false;
            }
            exisitingCar.setType(carDTO.getType());
            exisitingCar.setDescription(carDTO.getDescription());
            exisitingCar.setTransmission(carDTO.getTransmission());
            exisitingCar.setColor(carDTO.getColor());
            exisitingCar.setName(carDTO.getName());
            exisitingCar.setName(carDTO.getName());
            exisitingCar.setBrand(carDTO.getBrand());
            carRepository.save(exisitingCar);
            return true;
        }else {
            return false;
        }
    }
}
