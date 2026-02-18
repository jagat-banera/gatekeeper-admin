package com.gatekeeper.DatabaseSetup.projection;

import org.springframework.http.HttpMethod;

public interface ActiveRouteView {

    String getEndpoint();
    String getTargetUrl();
    String getHttpMethod();

}
