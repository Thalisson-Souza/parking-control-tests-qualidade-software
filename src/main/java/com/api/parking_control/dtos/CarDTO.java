package com.api.parking_control.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CarDTO(
        @NotBlank
        @Pattern(
                regexp = "^(?:[A-Z]{3}-\\d{4}|[A-Z]{3}\\d[A-Z]\\d{2})$",
                message = "A placa deve estar no padrao antigo (ABC-1234) ou Mercosul (ABC1D23)."
        )
        String plateCar,
        @NotBlank String modelCar,
        @NotBlank String colorCar )
{}
