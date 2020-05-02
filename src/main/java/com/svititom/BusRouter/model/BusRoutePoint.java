package com.svititom.BusRouter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;

@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
@Entity
@Table(name = "busRoutePoint")
public class BusRoutePoint {
    @Id
    @GeneratedValue
    private Long id;
    @JsonProperty("ServiceNo")
    private String serviceNumber;
    private String operator;
    private int direction;
    private int stopSequence;
//    @ManyToOne
//    @JoinColumn(name="bus_stop_code", referencedColumnName = "bus_stop_code")
//    private BusStop busStop;
//    @Column(name = "bus_stop_code")
    private String busStopCode;
    private int distance;
    // The following are strings, as '-' is used to indicate the bus does not run on that day
    @JsonProperty("WD_FirstBus")
    private String weekdayFirstBus;
    @JsonProperty("WD_LastBus")
    private String weekdayLastBus;
    @JsonProperty("SAT_FirstBus")
    private String satFirstBus;
    @JsonProperty("SAT_LastBus")
    private String satLastBus;
    @JsonProperty("SUN_FirstBus")
    private String sunFirstBus;
    @JsonProperty("SUN_LastBus")
    private String sunLastBus;

    @Override
    public String toString() {
        return "BusRoutePoint{" +
                "id=" + id +
                ", serviceNumber='" + serviceNumber + '\'' +
                ", operator='" + operator + '\'' +
                ", direction=" + direction +
                ", stopSequence=" + stopSequence +
                ", busStopCode=" + busStopCode +
                ", distance=" + distance +
                ", weekdayFirstBus='" + weekdayFirstBus + '\'' +
                ", weekdayLastBus='" + weekdayLastBus + '\'' +
                ", satFirstBus='" + satFirstBus + '\'' +
                ", satLastBus='" + satLastBus + '\'' +
                ", sunFirstBus='" + sunFirstBus + '\'' +
                ", sunLastBus='" + sunLastBus + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }



    public String getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(String serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getStopSequence() {
        return stopSequence;
    }

    public void setStopSequence(int stopSequence) {
        this.stopSequence = stopSequence;
    }

    public String getBusStopCode() {
        return busStopCode;
    }

    public void setBusStopCode(String busStopCode) {
        this.busStopCode = busStopCode;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getWeekdayFirstBus() {
        return weekdayFirstBus;
    }

    public void setWeekdayFirstBus(String weekdayFirstBus) {
        this.weekdayFirstBus = weekdayFirstBus;
    }

    public String getWeekdayLastBus() {
        return weekdayLastBus;
    }

    public void setWeekdayLastBus(String weekdayLastBus) {
        this.weekdayLastBus = weekdayLastBus;
    }

    public String getSatFirstBus() {
        return satFirstBus;
    }

    public void setSatFirstBus(String satFirstBus) {
        this.satFirstBus = satFirstBus;
    }

    public String getSatLastBus() {
        return satLastBus;
    }

    public void setSatLastBus(String satLastBus) {
        this.satLastBus = satLastBus;
    }

    public String getSunFirstBus() {
        return sunFirstBus;
    }

    public void setSunFirstBus(String sunFirstBus) {
        this.sunFirstBus = sunFirstBus;
    }

    public String getSunLastBus() {
        return sunLastBus;
    }

    public void setSunLastBus(String sunLastBus) {
        this.sunLastBus = sunLastBus;
    }
//      "ServiceNo": "10",
//              "Operator": "SBST",
//              "Direction": 1,
//              "StopSequence": 1,
//              "BusStopCode": "75009",
//              "Distance": 0,
//              "WD_FirstBus": "0500",
//              "WD_LastBus": "2300",
//              "SAT_FirstBus": "0500",
//              "SAT_LastBus": "2300",
//              "SUN_FirstBus": "0500",
//              "SUN_LastBus": "2300"
//},
}
