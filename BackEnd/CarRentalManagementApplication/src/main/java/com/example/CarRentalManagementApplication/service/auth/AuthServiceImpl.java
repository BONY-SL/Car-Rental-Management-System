package com.example.CarRentalManagementApplication.service.auth;

import com.example.CarRentalManagementApplication.dto.SignUpRequest;
import com.example.CarRentalManagementApplication.dto.UserDto;
import com.example.CarRentalManagementApplication.entity.User;
import com.example.CarRentalManagementApplication.repository.UserRepository;
import com.example.CarRentalManagementApplication.util.UserRole;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        user.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
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

    @PostConstruct
    public void createAdminAccount(){

        User adminAccount = userRepository.findByUserRole(UserRole.ADMIN);

        if(adminAccount == null){

            User newAdminAccount = new User();
            newAdminAccount.setName("admin");
            newAdminAccount.setEmail("admin12345@gmail.com");
            newAdminAccount.setPassword(new BCryptPasswordEncoder().encode("admin1234"));
            newAdminAccount.setUserRole(UserRole.ADMIN);
            userRepository.save(newAdminAccount);
            System.out.println("Admin Account Created Successfully");
        }
    }
}
