package com.svititom.BusRouter.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
// Magic required for SQLite
@TableGenerator(name="sqlite", table="sqlite_sequence",
        pkColumnName="name", valueColumnName="seq",
        pkColumnValue="sqliteTestTable")
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

    public void setBusStopCode(int busStopCode) {
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
