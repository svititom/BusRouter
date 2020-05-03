package com.svititom.BusRouter.model.lta;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;

@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class BusRoutePoint {
    @JsonProperty("ServiceNo")
    private String serviceNumber;
    private String operator;
    private int direction;
    private int stopSequence;


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


    public String getServiceNumber() {
        return serviceNumber;
    }

    public String getOperator() {
        return operator;
    }

    public int getDirection() {
        return direction;
    }


    public int getStopSequence() {
        return stopSequence;
    }


    public String getBusStopCode() {
        return busStopCode;
    }


    public int getDistance() {
        return distance;
    }

    public String getWeekdayFirstBus() {
        return weekdayFirstBus;
    }


    public String getWeekdayLastBus() {
        return weekdayLastBus;
    }


    public String getSatFirstBus() {
        return satFirstBus;
    }

    public String getSatLastBus() {
        return satLastBus;
    }

    public String getSunFirstBus() {
        return sunFirstBus;
    }

    public String getSunLastBus() {
        return sunLastBus;
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
