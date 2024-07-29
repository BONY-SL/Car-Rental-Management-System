package com.example.CarRentalManagementApplication.controller;

import com.example.CarRentalManagementApplication.dto.AuthenticationRequest;
import com.example.CarRentalManagementApplication.dto.AuthenticationResponse;
import com.example.CarRentalManagementApplication.dto.SignUpRequest;
import com.example.CarRentalManagementApplication.dto.UserDto;
import com.example.CarRentalManagementApplication.entity.User;
import com.example.CarRentalManagementApplication.repository.UserRepository;
import com.example.CarRentalManagementApplication.service.auth.AuthService;
import com.example.CarRentalManagementApplication.service.jwt.UserService;
import com.example.CarRentalManagementApplication.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUpCustomer(@RequestBody SignUpRequest signUpRequest){

        if(authService.hasCustomerWithEmail(signUpRequest.getEmail())){
            return new ResponseEntity<>("Customer Already exsist with this Email",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        UserDto userDto= authService.createCustomer(signUpRequest);
        if(userDto == null)return new ResponseEntity<>("Customer Not Created..", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(userDto,HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest){

        try {
            authenticationManager.authenticate
                    (new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));
        }catch (BadCredentialsException e){

            return new ResponseEntity<>("Incorrect User Name Or Password",
                    HttpStatus.NOT_ACCEPTABLE);
        }
        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());

        Optional<User> optionalUser = userRepository.findByEmail(userDetails.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        AuthenticationResponse authenticationResponse =  new AuthenticationResponse();
        if(optionalUser.isPresent()){
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserId(optionalUser.get().getId());
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
        }

        return new ResponseEntity<>(authenticationResponse,HttpStatus.CREATED);
    }
}
