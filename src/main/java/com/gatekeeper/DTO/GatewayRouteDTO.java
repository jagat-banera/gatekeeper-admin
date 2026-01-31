package com.gatekeeper.DTO;

public class GatewayRouteDTO {

    private String endpoint ;
    private String targetUrl;

    public GatewayRouteDTO(String endpoint, String targetUrl) {
        this.endpoint = endpoint;
        this.targetUrl = targetUrl;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }
}
