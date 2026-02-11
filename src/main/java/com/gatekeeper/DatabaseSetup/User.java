package com.gatekeeper.DatabaseSetup;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity(name = "User")
public class User {

    @Id
    @NotEmpty
    private String userId ;

    @NotEmpty
    private String password;

    private String role = "ROLE_USER";

    private Boolean isEnabled = true ;

    public User() {
    }

    public User(String userId, String password, Boolean isEnabled , String role) {
        this.userId = userId;
        this.password = password;
        this.isEnabled = isEnabled;
        this.role = role ;
    }



    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
