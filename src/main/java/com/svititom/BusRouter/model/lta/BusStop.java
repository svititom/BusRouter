package com.svititom.BusRouter.model.lta;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)

public class BusStop {
    @Id
    @GeneratedValue
    private Long id;

    // Apparently Bus stop code can be a non integer ... Specifically there are 2 bus stops called CTE ...
    private String busStopCode;
    private String roadName;
    private String description;
    private double latitude;
    private double longitude;



    public String getBusStopCode() {
        return busStopCode;
    }

    public void setBusStopCode(String busStopCode) {
        this.busStopCode = busStopCode;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusStop busStop = (BusStop) o;
        return busStopCode.equals(busStop.busStopCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(busStopCode);
    }

    @Override
    public String toString() {
        return "BusStop{" +
                "busStopCode=" + busStopCode +
                ", roadName='" + roadName + '\'' +
                ", description='" + description + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
