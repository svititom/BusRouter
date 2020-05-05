package com.svititom.BusRouter.model.lta;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.svititom.BusRouter.model.lta.BusStop;

import java.util.LinkedList;
import java.util.List;

/**
 * Pojo for deserializing BusStops from Lta Api
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusStops {
    private String odataMetadata;
    @JsonProperty("value")
    private List<BusStop> busStops = new LinkedList<>();

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
