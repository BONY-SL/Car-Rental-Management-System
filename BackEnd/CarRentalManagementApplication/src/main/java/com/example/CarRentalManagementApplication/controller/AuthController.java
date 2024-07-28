package com.example.CarRentalManagementApplication.controller;



import com.example.CarRentalManagementApplication.dto.SignUpRequest;
import com.example.CarRentalManagementApplication.dto.UserDto;
import com.example.CarRentalManagementApplication.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUpCustomer(@RequestBody SignUpRequest signUpRequest){

        UserDto userDto= authService.createCustomer(signUpRequest);

        if(userDto == null)return new ResponseEntity<>("Customer Not Created..", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(userDto,HttpStatus.CREATED);
    }
}
