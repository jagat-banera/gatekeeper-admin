package com.gatekeeper.Service;

import com.gatekeeper.DTO.RouteDTO;
import com.gatekeeper.DatabaseSetup.RouteMapper;
import com.gatekeeper.DatabaseSetup.RouteRepo;
import com.gatekeeper.DatabaseSetup.projection.ActiveRouteView;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteService {

    private final RouteRepo routeRepo ;

    public RouteService(RouteRepo routeRepo) {
        this.routeRepo = routeRepo;
    }


    public List<RouteDTO> showAllRoutes(){

        List<RouteMapper> routes = routeRepo.findAll();

        return routes.stream().map(this::convertToDTO).collect(Collectors.toList());

    }

    public void registerRoute(RouteDTO routeDTO){

        RouteMapper routeMapper = convertToEntity(routeDTO);

        routeRepo.save(routeMapper);

    }

    public void activateAPI(Integer id){
        routeRepo.activateRoute(id);
    }

    public void deactivateAPI(Integer id){
        routeRepo.deactivateRoute(id);
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
