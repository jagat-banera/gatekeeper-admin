package com.gatekeeper.Service;

import com.gatekeeper.DTO.GatewayDTOs.ActiveRouteRecord;
import com.gatekeeper.DTO.GatewayDTOs.Route;
import com.gatekeeper.DTO.GatewayDTOs.RouteKey;
import com.gatekeeper.DTO.GatewayRouteDTO;
import com.gatekeeper.DTO.RouteDTO;
import com.gatekeeper.DatabaseSetup.RouteMapper;
import com.gatekeeper.DatabaseSetup.RouteRepo;
import com.gatekeeper.DatabaseSetup.projection.ActiveRouteView;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.LinkedList;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class RouteService {

    private final RouteRepo routeRepo ;
    private final RestTemplate restTemplate ;

    @Value("${gatekeeper.gateway.url}")
    private String gatewayUrl;

    public RouteService(RouteRepo routeRepo, RestTemplate restTemplate) {
        this.routeRepo = routeRepo;
        this.restTemplate = restTemplate;
    }


    public List<RouteDTO> showAllRoutes(){

        List<RouteMapper> routes = routeRepo.findAll();

        return routes.stream().map(this::convertToDTO).collect(Collectors.toList());

    }

    public void registerRoute(RouteDTO routeDTO){

        RouteMapper routeMapper = convertToEntity(routeDTO);

        routeRepo.saveAndFlush(routeMapper);

    }

    public String activateAPI(Integer id){
        // Update in the DB first
        routeRepo.activateRoute(id);

        // Update it in the Gateway
        // Call the gateway endpoint "/gateway/add-route"
        // find the Gateway Route by ID


        // The conversion pf GatewayRouteDTO to ActiveRouteRecord is done below
        // This is done to send data in the same format to add on the gateway end
        // that has been used to load routes on Startup

        ActiveRouteView routeDTO = routeRepo.findGatewayRoute(id);

        ActiveRouteRecord activeRouteRecord = new ActiveRouteRecord(
                new RouteKey(routeDTO.getEndpoint(),routeDTO.getHttpMethod()),
                new Route(routeDTO.getTargetUrl())
        );




        System.out.println("Gateway URL from properties: " + gatewayUrl);

        System.out.println(activeRouteRecord);

        ResponseEntity<String> response = restTemplate.postForEntity(gatewayUrl + "/gateway/add-route", activeRouteRecord,
                String.class);

        if(response.getStatusCode().is2xxSuccessful()){
            System.out.println("Route Activated SuccessFully");
        }

        return response.getBody() ;

    }


    public String deactivateApi(Integer id){
        // Update in the DB First
        routeRepo.deactivateRoute(id);

        // Update it in the Gateway
        // Call the gateway endpoint "/gateway/remove-route"
        // find the Gateway Route by ID


        ActiveRouteView routeDTO = routeRepo.findGatewayRoute(id);

        ResponseEntity<String> response = restTemplate.postForEntity(gatewayUrl + "/gateway/remove-route", routeDTO,
                String.class);

        if(response.getStatusCode().is2xxSuccessful()){
            System.out.println(response.getBody());
        }

        return response.getBody() ;

    }



    public List<ActiveRouteRecord> getGatewayRoutes(){

        List<ActiveRouteRecord> activeRouteRecords = new LinkedList<>();

        // This gets the DB data of all Active Routes that will be send to Gateway Over HTTP
        List<ActiveRouteView> activeRouteViews = routeRepo.gatewayRoutes();

        activeRouteViews.forEach(route ->
                activeRouteRecords.add(
                        new ActiveRouteRecord(
                                new RouteKey(route.getEndpoint(), route.getHttpMethod()),
                                new Route(route.getTargetUrl())
                        )
                )
                );

        return activeRouteRecords ;


    }



    // Utils

    private RouteDTO convertToDTO(RouteMapper entity) {
        RouteDTO dto = new RouteDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setVersion(entity.getVersion());
        dto.setEndpoint(entity.getEndpoint());
        dto.setTargetUrl(entity.getTargetUrl());
        dto.setActive(entity.isActive());
        return dto;
    }

    private RouteMapper convertToEntity(RouteDTO dto) {
        RouteMapper entity = new RouteMapper();

        // For new entities, id will be null and auto-generated
        entity.setId(dto.getId()); // Only needed if you're updating existing record
        entity.setName(dto.getName());
        entity.setVersion(dto.getVersion());
        entity.setEndpoint(dto.getEndpoint());
        entity.setTargetUrl(dto.getTargetUrl());
        entity.setActive(dto.isActive());

        return entity;
    }


}
