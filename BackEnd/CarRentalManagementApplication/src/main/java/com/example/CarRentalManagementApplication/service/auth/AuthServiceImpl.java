package com.example.CarRentalManagementApplication.service.auth;

import com.example.CarRentalManagementApplication.dto.SignUpRequest;
import com.example.CarRentalManagementApplication.dto.UserDto;
import com.example.CarRentalManagementApplication.entity.User;
import com.example.CarRentalManagementApplication.repository.UserRepository;
import com.example.CarRentalManagementApplication.util.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    @Override
    public UserDto createCustomer(SignUpRequest signUpRequest) {

        User user = new User();

        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setUserRole(UserRole.CUSTOMER);



        User CreatedUser = userRepository.save(user);

        UserDto userDto =new UserDto();

        userDto.setId(CreatedUser.getId());
        userDto.setName(CreatedUser.getName());
        userDto.setEmail(CreatedUser.getEmail());
        userDto.setPassword(CreatedUser.getPassword());
        userDto.setUserRole(CreatedUser.getUserRole());


        return userDto;
    }

    @Override
    public boolean hasCustomerWithEmail(String email) {

        return userRepository.findByEmail(email).isPresent();
    }
}
