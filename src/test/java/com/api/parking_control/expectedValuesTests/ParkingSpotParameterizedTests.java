package com.api.parking_control.expectedValuesTests;

import com.api.parking_control.entity.ParkingSpot;
import com.api.parking_control.repository.ParkingSpotRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ParkingSpotParameterizedTests {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @ParameterizedTest
    @Tag("parameterized")
    @Tag("expected-values")
    @DisplayName("Parameterized: deve validar vagas e responsaveis esperados do banco")
    @CsvSource({
            "A, 01, Carlos Souza",
            "A, 03, Marcos Oliveira",
            "A, 05, Rafael Costa",
            "A, 10, Camila Ferreira"
    })
    void deveValidarVagasComValoresEsperados(String block, String spotNumber, String responsibleName) {
        ParkingSpot result = parkingSpotRepository.findByBlockAndParkingSpotNumber(block, spotNumber);

        assertNotNull(result);
        assertEquals(block, result.getBlock());
        assertEquals(spotNumber, result.getParkingSpotNumber());
        assertEquals(responsibleName, result.getResponsibleName());
        assertEquals(ParkingSpot.Status.RESERVED, result.getStatus());
    }

    @ParameterizedTest
    @Tag("parameterized")
    @Tag("expected-values")
    @DisplayName("Parameterized: deve validar placas existentes no banco")
    @CsvSource({
            "ZGG-4366",
            "HZT-7130",
            "NDG-8451"
    })
    void deveValidarPlacasExistentes(String plateCar) {
        boolean exists = parkingSpotRepository.existsByCarPlateCar(plateCar);

        assertTrue(exists);
    }

    @ParameterizedTest
    @Tag("parameterized")
    @Tag("expected-values")
    @DisplayName("Parameterized: deve validar vagas existentes no banco")
    @CsvSource({
            "A, 01",
            "A, 03",
            "A, 05",
            "A, 10"
    })
    void deveValidarVagasExistentes(String block, String spotNumber) {
        boolean exists = parkingSpotRepository.existsByBlockAndParkingSpotNumber(block, spotNumber);

        assertTrue(exists);
    }

}
