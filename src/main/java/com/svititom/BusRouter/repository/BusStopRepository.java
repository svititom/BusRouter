package com.svititom.BusRouter.repository;


import com.svititom.BusRouter.model.lta.BusStop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusStopRepository extends JpaRepository<BusStop, Integer> {
}
