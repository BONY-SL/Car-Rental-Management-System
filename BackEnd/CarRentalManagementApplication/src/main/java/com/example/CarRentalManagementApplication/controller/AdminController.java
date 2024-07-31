package com.example.CarRentalManagementApplication.controller;

import com.example.CarRentalManagementApplication.dto.CarDTO;
import com.example.CarRentalManagementApplication.dto.GetBookingCarDTO;
import com.example.CarRentalManagementApplication.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;


    @PostMapping("/postCar")
    public ResponseEntity<?> postCar(@ModelAttribute CarDTO carDTO) throws IOException {

        System.out.println(carDTO);
        boolean success = adminService.postCar(carDTO);

        if(success){
            return  ResponseEntity.status(HttpStatus.CREATED).build();
        }else {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/getAllCars")
    public ResponseEntity<?> getAllCars(){

        return ResponseEntity.ok(adminService.getAllCars());
    }

    @DeleteMapping("/deleteCar/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Integer id){
        adminService.deleteCar(id);

        return ResponseEntity.ok(null);
    }

    @GetMapping("/getCarById/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Integer id){

        CarDTO carDTO = adminService.getCarById(id);

        return ResponseEntity.ok(carDTO);

    }

    @PutMapping("/updateCarById/{id}")
    public ResponseEntity<?> updateCarById(@PathVariable Integer id,@ModelAttribute CarDTO carDTO) throws IOException {

        System.out.println(carDTO);
        try{
            boolean success = adminService.updateCarById(id,carDTO);
            if(success)return ResponseEntity.status(HttpStatus.OK).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @GetMapping("/getBookingCarsByCustomer")
    public ResponseEntity<List<GetBookingCarDTO>> getBookingCarsByCustomer(){
        return ResponseEntity.ok(adminService.getBookingCarsByCustomer());
    }

}
