package com.svititom.BusRouter.service;

import com.svititom.BusRouter.model.BusRoute;

import java.util.List;

public interface BusRouteService {
    public void updateBusRoutes();
    public BusRoute getBusRoute(String serviceNumber, int direction);
    public List<BusRoute> getAllBusRoutes();
}
