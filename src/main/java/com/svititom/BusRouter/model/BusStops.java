package com.svititom.BusRouter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Pojo for deserializing BusStops from Lta Api
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusStops {
    private String odataMetadata;
    @JsonProperty("value")
    private List<BusStop> busStops;

    public String getOdataMetadata() {
        return odataMetadata;
    }

    public void setOdataMetadata(String odataMetadata) {
        this.odataMetadata = odataMetadata;
    }

    public List<BusStop> getBusStops() {
        return busStops;
    }

    public void setBusStops(List<BusStop> busStops) {
        this.busStops = busStops;
    }

    @Override
    public String toString() {
        return "BusStops{" +
                "odataMetadata='" + odataMetadata + '\'' +
                ", value=" + busStops +
                '}';
    }
}
