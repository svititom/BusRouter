package com.svititom.BusRouter.model;

import com.svititom.BusRouter.model.lta.BusStop;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a single route in a single direction
 * E.g. Route 8, starting at 75009, Tampines int going to 52009, Toa Payoh Int
 * The inverse route, from toa payoh to tampines is another BusRoute
 * This is because opposite direction bus routes can go through different paths, or there are circular paths
 *
 * For now we disregard bus stop first and last bus
 */
@Entity
public class BusRoute{
    @Id
    @GeneratedValue
    private Long Id;

    private String serviceNumber;
    private String operator;
    private int direction;

    // We can afford eager, it's "just 10~40 bus stops, and we do need them
    // Todo find if we can get these at a later time
    @ManyToMany(fetch = FetchType.EAGER)
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

    public List<BusStop> getBusStops() {
        return busStops;
    }

    public Long getId() {
        return Id;
    }

    @Override
    public String toString() {
        return "BusRoute{" +
                "Id=" + Id +
                ", serviceNumber='" + serviceNumber + '\'' +
                ", operator='" + operator + '\'' +
                ", direction=" + direction +
                ", busStops=" + this.getBusStops() +
                '}';
    }
}