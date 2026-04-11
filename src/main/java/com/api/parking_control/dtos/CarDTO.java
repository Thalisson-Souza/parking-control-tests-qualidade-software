package com.api.parking_control.dtos;

import jakarta.validation.constraints.NotBlank;

public record CarDTO(
    @NotBlank String plateCar,
    @NotBlank String modelCar,
    @NotBlank String colorCar
    )
{}
