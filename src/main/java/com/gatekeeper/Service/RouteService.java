package com.gatekeeper.Service;

import com.gatekeeper.DTO.GatewayRouteDTO;
import com.gatekeeper.DTO.RouteDTO;
import com.gatekeeper.DatabaseSetup.RouteMapper;
import com.gatekeeper.DatabaseSetup.RouteRepo;
import com.gatekeeper.DatabaseSetup.projection.ActiveRouteView;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RouteService {

    private final RouteRepo routeRepo ;
    private final RestTemplate restTemplate ;

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

        routeRepo.save(routeMapper);

    }

    public String activateAPI(Integer id){
        // Update in the DB first
        routeRepo.activateRoute(id);

        // Update it in the Gateway
        // Call the gateway endpoint "/gateway/add-route"
        // find the Gateway Route by ID

        GatewayRouteDTO routeDTO = routeRepo.findGatewayRoute(id);

        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8082/gateway/add-route", routeDTO,
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


        GatewayRouteDTO routeDTO = routeRepo.findGatewayRoute(id);

        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8082/gateway/remove-route", routeDTO,
                String.class);

        if(response.getStatusCode().is2xxSuccessful()){
            System.out.println(response.getBody());
        }

        return response.getBody() ;

    }



    public List<ActiveRouteView> getGatewayRoutes(){
        return routeRepo.gatewayRoutes();
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
