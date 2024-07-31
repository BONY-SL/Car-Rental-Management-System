package com.example.CarRentalManagementApplication.controller;

import com.example.CarRentalManagementApplication.dto.BookCarDTO;
import com.example.CarRentalManagementApplication.dto.CarDTO;
import com.example.CarRentalManagementApplication.dto.GetBookingCarDTO;
import com.example.CarRentalManagementApplication.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @GetMapping("/getAllCarsForCustomer")
    public ResponseEntity<List<CarDTO>> getAllCars(){

        List<CarDTO> carDTOList = customerService.getAllCarsForCustomer();

        return ResponseEntity.ok(carDTOList);
    }

    @PostMapping("/bookCar")
    public ResponseEntity<?> bookCar(@RequestBody BookCarDTO bookCarDTO){

        System.out.println(bookCarDTO);
        boolean success = customerService.bookCar(bookCarDTO);
        if(success){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/getCarById/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Integer id){

        CarDTO carDTO = customerService.getCarById(id);
        return ResponseEntity.ok(carDTO);

    }

    @GetMapping("/getBookingCarByUserId/{userId}")
    public ResponseEntity<List<GetBookingCarDTO>> getBookingCarByUserId(@PathVariable Integer userId){

        return ResponseEntity.ok(customerService.getBookingsByUserId(userId));
    }


}
