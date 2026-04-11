package com.api.parking_control.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ParkingSpotDTO(
    @NotBlank String block,
    @NotBlank String parkingSpotNumber,
    @NotBlank String responsibleName,
    @Valid @NotNull CarDTO car
    )
{}
