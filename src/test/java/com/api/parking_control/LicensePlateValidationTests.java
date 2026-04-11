package com.api.parking_control;

import com.api.parking_control.dtos.CarDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LicensePlateValidationTests {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    @DisplayName("tem que aceitar placa no formato antigo (ABC-1234")
    void deveAceitarPlacaAntiga() {
        CarDTO dto = new CarDTO("ABC-1234", "Civic", "Prata");

        Set<ConstraintViolation<CarDTO>> violations = validator.validate(dto);

        assertEquals(0, violations.size());
    }

    @Test
    @DisplayName("tem que aceitar placa no formato Mercosul (BRA1E23")
    void deveAceitarPlacaMercosul() {
        CarDTO dto = new CarDTO("BRA1E23", "Civic", "Prata");

        Set<ConstraintViolation<CarDTO>> violations = validator.validate(dto);

        assertEquals(0, violations.size());
    }

    @Test
    @DisplayName("tem que rejeitar placa invalida")
    void deveRejeitarPlacaInvalida() {
        CarDTO dto = new CarDTO("NAO-SEIU", "Civic", "Prata");

        Set<ConstraintViolation<CarDTO>> violations = validator.validate(dto);

        assertEquals(1, violations.size());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("plateCar")));
    }
}
