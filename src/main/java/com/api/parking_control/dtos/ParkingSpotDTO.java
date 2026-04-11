package com.api.parking_control.dtos;

import ch.qos.logback.core.status.Status;
import com.api.parking_control.entity.ParkingSpot;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ParkingSpotDTO(
    @NotBlank String parkingSpotNumber,
    @NotBlank String responsibleName,
    @NotBlank String block,
    @NotNull CarDTO car,
    String status
    )
{}
