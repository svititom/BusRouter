package com.svititom.BusRouter.repository;

import com.svititom.BusRouter.model.BusRoute;
import org.springframework.data.jpa.repository.JpaRepository;

// This is the magic of Jpa, we just tell it the entity, and define getXBy functions, and Jpa will just implement them for us <3
public interface BusRouteRepository extends JpaRepository<BusRoute, Long> {
    public BusRoute getBusRouteByServiceNumberAndDirection(String serviceNumber, int direction);

}
