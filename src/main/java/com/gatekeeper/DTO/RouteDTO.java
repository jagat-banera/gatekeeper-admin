package com.gatekeeper.DTO;

import com.gatekeeper.utils.ValidURL;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Name;
import lombok.Getter;
import lombok.Setter;


public class RouteDTO {

    private Integer id ;

    @NotEmpty(message = "Name cannot be Empty")
    private String name ;

    private Integer version = 1;

    @NotEmpty(message = "Endpoint Cannot be Empty")
    private String endpoint;

    @ValidURL(message = "URL is not Valid")
    private String targetUrl;

    private boolean active = false ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
