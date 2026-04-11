package com.api.parking_control.repository;

import com.api.parking_control.entity.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {

    boolean existsByCarPlateCar(String plateCar);
    boolean existsByBlockAndParkingSpotNumber(String block, String parkingSpotNumber);

    ParkingSpot findByResponsibleName(String responsibleName);

    ParkingSpot findByBlockAndParkingSpotNumber(String block, String spotNumber);
}
