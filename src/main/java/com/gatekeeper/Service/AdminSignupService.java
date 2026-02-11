package com.gatekeeper.Service;

import com.gatekeeper.DTO.AdminSignupRequest;
import com.gatekeeper.DatabaseSetup.User;
import com.gatekeeper.DatabaseSetup.userRepository;
import com.gatekeeper.Service.Exceptions.AdminAlreadyExistsException;
import com.gatekeeper.Service.Exceptions.PasswordMismtachException;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminSignupService {

    private final userRepository userRepo ;
    private final PasswordEncoder encoder ;


    public AdminSignupService(userRepository userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @Transactional
    public void createAdmin(AdminSignupRequest request){

        // If the Password and Confirm Password Feild does not Match
        if(!request.getPassword().equals(request.getConfirmedPassword())){
            throw new PasswordMismtachException();
        }

        // If the Admin Already Exists
        if(userRepo.existsByRole("ROLE_ADMIN")){
            throw new AdminAlreadyExistsException();
        }

        // Add the user in DB as "ROLE_ADMIN"
        else{

            // Use Password Encoder
            User adminUser = new User(
                    request.getUsername(),
                    encoder.encode(request.getPassword()),
                    true,
                    "ROLE_ADMIN"
            );

            userRepo.save(adminUser);

        }

    }
}
