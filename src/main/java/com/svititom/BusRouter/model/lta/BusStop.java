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
    private int busStopCode;
    private String roadName;
    private String description;
    private double latitude;
    private double longitude;


    public int getBusStopCode() {
        return busStopCode;
    }

    public String getRoadName() {
        return roadName;
    }

    public String getDescription() {
        return description;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
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
