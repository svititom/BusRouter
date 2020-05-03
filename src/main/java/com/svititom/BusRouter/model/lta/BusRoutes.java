package com.svititom.BusRouter.model.lta;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Pojo for desereilaizing Bus Routes from Lta
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusRoutes {

    private String odataMetadata;
    @JsonProperty("value")
    private List<BusRoutePoint> busRoutePoints;

    @Override
    public String toString() {
        return "BusRoutes{" +
                "odataMetadata='" + odataMetadata + '\'' +
                ", busRoutePoints=" + busRoutePoints +
                '}';
    }

    public String getOdataMetadata() {
        return odataMetadata;
    }

    public void setOdataMetadata(String odataMetadata) {
        this.odataMetadata = odataMetadata;
    }

    public List<BusRoutePoint> getBusRoutePoints() {
        return busRoutePoints;
    }

    public void setBusRoutePoints(List<BusRoutePoint> busRoutePoints) {
        this.busRoutePoints = busRoutePoints;
    }
}
