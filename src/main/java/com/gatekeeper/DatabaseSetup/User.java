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

    private Boolean isEnabled = true ;

    public User(String userId, String password, Boolean isEnabled) {
        this.userId = userId;
        this.password = password;
        this.isEnabled = isEnabled;
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


}
