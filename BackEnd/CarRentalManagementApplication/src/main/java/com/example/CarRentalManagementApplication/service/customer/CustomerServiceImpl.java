package com.example.CarRentalManagementApplication.service.customer;

import com.example.CarRentalManagementApplication.dto.BookCarDTO;
import com.example.CarRentalManagementApplication.dto.CarDTO;
import com.example.CarRentalManagementApplication.dto.GetBookingCarDTO;
import com.example.CarRentalManagementApplication.entity.BookedCar;
import com.example.CarRentalManagementApplication.entity.Car;
import com.example.CarRentalManagementApplication.entity.User;
import com.example.CarRentalManagementApplication.repository.BookCarRepository;
import com.example.CarRentalManagementApplication.repository.CarRepository;
import com.example.CarRentalManagementApplication.repository.UserRepository;
import com.example.CarRentalManagementApplication.util.BookCarStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final BookCarRepository bookCarRepository;

    @Override
    public List<CarDTO> getAllCarsForCustomer() {
        return carRepository.findAll().stream().map(Car::getCarDTO).collect(Collectors.toList());
    }

    @Override
    public boolean bookCar(BookCarDTO bookCarDTO) {

        try {
            Optional<Car> optionalCar = carRepository.findById(bookCarDTO.getCarId());
            Optional<User> optionalUser = userRepository.findById(bookCarDTO.getUserId());

            if (optionalCar.isPresent() && optionalUser.isPresent()) {

                Car eXcar = optionalCar.get();

                BookedCar bookedCar = new BookedCar();

                bookedCar.setUser(optionalUser.get());
                bookedCar.setCar(eXcar);
                bookedCar.setBookCarStatus(BookCarStatus.PENDING);
                long timeDuration = bookCarDTO.getToDate().getTime() - bookCarDTO.getFromDate().getTime();
                long days = TimeUnit.MILLISECONDS.toDays(timeDuration);
                bookedCar.setDays(days);
                bookedCar.setPrice(eXcar.getPrice() * days);
                bookedCar.setFromDate(bookCarDTO.getFromDate());
                bookedCar.setToDate(bookCarDTO.getToDate());

                bookCarRepository.save(bookedCar);
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public CarDTO getCarById(Integer id) {
        Optional<Car> optionalCar =  carRepository.findById(id);
        return optionalCar.map(Car::getCarDTO).orElse(null);
    }

    @Override
    public List<GetBookingCarDTO> getBookingsByUserId(Integer userId) {

        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User Not Found"));

        return bookCarRepository.findAllByUser(user).stream().map(BookedCar::getBookingCarDTO).collect(Collectors.toList());
    }
}
