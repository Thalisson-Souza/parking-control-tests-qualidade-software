package com.api.parking_control.repository;

import com.api.parking_control.entity.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {

    boolean existsByCarPlateCar(String plateCar);
    boolean existsByParkingSpotNumber(String parkingSpotNumber);
    boolean existsByBlock(String licenseBlockSpot);

    ParkingSpot findByResponsibleName(String responsibleName);

    ParkingSpot findByParkingSpotNumber(String spotNumber);
}
