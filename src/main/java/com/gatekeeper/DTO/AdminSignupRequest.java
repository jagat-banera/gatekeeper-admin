package com.gatekeeper.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AdminSignupRequest {

    @NotBlank(message = "Username is Required")
    @Size(min = 5 , max = 15 , message = "Username Must be 5 to 15 characters")
    private String username ;



    @NotBlank(message = "Password is Required")
    @Size(min = 5 , max = 10 , message = "Username Must be 5 to 10 characters")
    private String password ;


    @NotBlank(message = "Password Does Not Match")
    private String confirmedPassword ;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedpassword) {
        this.confirmedPassword = confirmedpassword;
    }
}
