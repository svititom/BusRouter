package com.svititom.BusRouter.service;

import com.svititom.BusRouter.model.BusRoute;
import com.svititom.BusRouter.model.lta.BusRoutes;

public interface BusRouteService {
    public void updateBusRoutes(BusRoutes busRoutes);
    public BusRoute getBusRoute(String serviceNumber, int direction);
}
