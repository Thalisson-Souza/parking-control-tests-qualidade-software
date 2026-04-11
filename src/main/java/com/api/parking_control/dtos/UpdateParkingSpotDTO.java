package com.api.parking_control.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record UpdateParkingSpotDTO(
        @NotBlank Long id,
        @NotBlank String responsibleName,
        @NotBlank String plateCar,
        @NotBlank String modelCar,
        @NotBlank String colorCar
    )
{}
