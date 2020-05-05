package com.svititom.BusRouter.model;

import com.svititom.BusRouter.model.lta.BusStop;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class represents a single route in a single direction
 * E.g. Route 8, starting at 75009, Tampines int going to 52009, Toa Payoh Int
 * The inverse route, from toa payoh to tampines is another BusRoute
 * This is because opposite direction bus routes can go through different paths, or there are circular paths
 *
 * For now we disregard bus stop first and last bus
 */
@Entity
public class BusRoute {
    /*
    We use a composite key of serviceNumber and direction,
    since a service can have multiple directions
    */
    @Id
    private Integer id;

    private String serviceNumber;
    private int direction;
    private String operator;

    // We can afford eager, it's "just 10~40 bus stops, and we do need them
    // Todo find if we can get these at a later time
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="bus_stop_route",
            joinColumns = @JoinColumn(name="id"),
            inverseJoinColumns = @JoinColumn(name="bus_stop_code")
    )
    private List<BusStop> busStops;

    public BusRoute(){}
    public BusRoute(String serviceNumber, String operator, int direction) {
        this.serviceNumber = serviceNumber;
        this.operator = operator;
        this.direction = direction;
        busStops = new ArrayList<>();
        createId();
    }

    // This is a crutch until we find a more elegant way for jpa to compare objects
    public void createId(){
        this.id = hashCode();
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


    public List<BusStop> getBusStops() {
        return busStops;
    }

    @Override
    public String toString() {
        return "BusRoute{" +
                "serviceNumber='" + serviceNumber + '\'' +
                ", direction=" + direction +
                ", operator='" + operator + '\'' +
                '}';
    }

    public String toStringWithBusStops(){
        return "BusRoute{" +
                "serviceNumber='" + serviceNumber + '\'' +
                ", direction=" + direction +
                ", operator='" + operator + '\'' +
                ", busStops='" + busStops + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusRoute busRoute = (BusRoute) o;
        return getDirection() == busRoute.getDirection() &&
                getServiceNumber().equals(busRoute.getServiceNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getServiceNumber(), getDirection());
    }
}