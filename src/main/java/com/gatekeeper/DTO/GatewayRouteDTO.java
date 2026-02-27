package com.gatekeeper.DTO;

public class GatewayRouteDTO {

    private String endpoint ;
    private String targetUrl;
    private String httpMethod ;

    public GatewayRouteDTO(String endpoint, String targetUrl,String httpMethod) {
        this.endpoint = endpoint;
        this.targetUrl = targetUrl;
        this.httpMethod = httpMethod ;
    }

    public GatewayRouteDTO() {
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

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }
}
