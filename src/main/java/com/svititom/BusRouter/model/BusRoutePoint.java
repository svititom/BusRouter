package com.svititom.BusRouter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class BusRoutePoint {
    private int serviceNumber;
    private String operator;
    private int direction;
    private int stopSequence;
    private int busStopCode;
    private int distance;
    @JsonProperty("WD_FirstBus")
    private int weekdayFirstBus;
    @JsonProperty("WD_LastBus")
    private int weekdayLastBus;
    @JsonProperty("SAT_FirstBus")
    private int satFirstBus;
    @JsonProperty("SAT_LastBus")
    private int satLastBus;
    @JsonProperty("SUN_FirstBus")
    private int sunFirstBus;
    @JsonProperty("SUN_LastBus")
    private int sunLastBus;



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
