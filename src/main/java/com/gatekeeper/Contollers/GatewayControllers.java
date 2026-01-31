package com.gatekeeper.Contollers;


import com.gatekeeper.DatabaseSetup.projection.ActiveRouteView;
import com.gatekeeper.Service.RouteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// These are the list of controllers / endpoints which are only oy be called my gateway
// to request data from the admin server.

@RestController
@RequestMapping("/gateway")
public class GatewayControllers {

    private final RouteService routeService ;

    public GatewayControllers(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/routes")
    public List<ActiveRouteView> getActiveRoutes(){

        return routeService.getGatewayRoutes() ;

    }

}
