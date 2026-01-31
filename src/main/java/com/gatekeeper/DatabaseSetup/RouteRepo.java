package com.gatekeeper.DatabaseSetup;

import com.gatekeeper.DTO.GatewayRouteDTO;
import com.gatekeeper.DatabaseSetup.projection.ActiveRouteView;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouteRepo extends JpaRepository<RouteMapper,Long> {

    List<RouteMapper> findByActiveTrue();

    //Activate Route
    @Modifying
    @Transactional
    @Query("UPDATE RouteMapper r SET r.active = true WHERE r.id = :id")
    void activateRoute(@Param("id") Integer id);


    //Deactivate Route
    @Modifying
    @Transactional
    @Query("UPDATE RouteMapper r SET r.active = false WHERE r.id = :id")
    void deactivateRoute(@Param("id") Integer id);


    // ----------------------- GATEWAY QUERIES--------------------------------------//

    @Query("""
       SELECT r.endpoint AS endpoint,
              r.targetUrl AS targetUrl
       FROM RouteMapper r
       WHERE r.active = true
       """)
    List<ActiveRouteView> gatewayRoutes();


    @Query("""
            SELECT r.endpoint AS endpoint,
                   r.targetUrl AS targetUrl
            FROM RouteMapper r
            WHERE r.id = :id
            """
    )
    GatewayRouteDTO findGatewayRoute(@Param("id") Integer id);








}



