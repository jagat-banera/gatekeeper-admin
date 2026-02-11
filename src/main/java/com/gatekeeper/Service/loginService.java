package com.gatekeeper.Service;


import com.gatekeeper.DatabaseSetup.User;
import com.gatekeeper.DatabaseSetup.userRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class loginService implements UserDetailsService {


    private final userRepository userRepo ;

    public loginService(PasswordEncoder encoder, userRepository userRepo) {
        this.userRepo = userRepo;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User dbUser = userRepo.findByUserId(username).orElseThrow(()-> {
            return new UsernameNotFoundException("Invalid Username or Password");
        });

        return org.springframework.security.core.userdetails.User
                .withUsername(dbUser.getUserId())
                .password(dbUser.getPassword())
                .disabled(!dbUser.getEnabled())
                .authorities(dbUser.getRole())
                .build();

    }
}
