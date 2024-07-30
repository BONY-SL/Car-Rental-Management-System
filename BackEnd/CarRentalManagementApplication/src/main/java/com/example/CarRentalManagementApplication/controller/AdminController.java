package com.example.CarRentalManagementApplication.controller;

import com.example.CarRentalManagementApplication.dto.CarDTO;
import com.example.CarRentalManagementApplication.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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

}
