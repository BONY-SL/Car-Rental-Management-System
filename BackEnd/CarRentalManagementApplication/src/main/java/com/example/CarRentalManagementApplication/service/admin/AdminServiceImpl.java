package com.example.CarRentalManagementApplication.service.admin;

import com.example.CarRentalManagementApplication.dto.CarDTO;
import com.example.CarRentalManagementApplication.dto.CarDTOList;
import com.example.CarRentalManagementApplication.dto.GetBookingCarDTO;
import com.example.CarRentalManagementApplication.dto.SearchCarDTO;
import com.example.CarRentalManagementApplication.entity.BookedCar;
import com.example.CarRentalManagementApplication.entity.Car;
import com.example.CarRentalManagementApplication.repository.BookCarRepository;
import com.example.CarRentalManagementApplication.repository.CarRepository;
import com.example.CarRentalManagementApplication.util.BookCarStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final CarRepository carRepository;

    private final BookCarRepository bookCarRepository;

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

    @Override
    public List<GetBookingCarDTO> getBookingCarsByCustomer() {
        return bookCarRepository.findAll().stream().map(BookedCar::getBookingCarDTO).collect(Collectors.toList());
    }

    @Override
    public boolean changeBookingStatus(Integer bookingId, String status) {

        Optional<BookedCar> optionalBookedCar = bookCarRepository.findById(bookingId);

        if(optionalBookedCar.isPresent()){

            BookedCar bookedCar = optionalBookedCar.get();

            if(Objects.equals(status,"APPROVED")){
                bookedCar.setBookCarStatus(BookCarStatus.APPROVED);
            }else {
                bookedCar.setBookCarStatus(BookCarStatus.REJECTED);
            }
            bookCarRepository.save(bookedCar);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public CarDTOList searchCar(SearchCarDTO searchCarDTO) {
        Car car = new Car();
        car.setBrand(searchCarDTO.getBrand());
        car.setType(searchCarDTO.getType());
        car.setTransmission(searchCarDTO.getTransmission());
        car.setColor(searchCarDTO.getColor());

        ExampleMatcher exampleMatcher =
                ExampleMatcher.matchingAll()
                        .withMatcher("brand",ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                        .withMatcher("type",ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                        .withMatcher("transmission",ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                        .withMatcher("color",ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        Example<Car> carExample = Example.of(car,exampleMatcher);

        List<Car> carList = carRepository.findAll(carExample);

        CarDTOList carDTOList = new CarDTOList();

        carDTOList.setCarDTOList(carList.stream().map(Car::getCarDTO).collect(Collectors.toList()));
        return carDTOList;
    }
}
