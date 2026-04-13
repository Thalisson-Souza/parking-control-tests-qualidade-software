package com.api.parking_control.expectedValuesTests;

import com.api.parking_control.dtos.ParkingSpotDTO;
import com.api.parking_control.entity.ParkingSpot;
import com.api.parking_control.repository.ParkingSpotRepository;
import com.api.parking_control.services.ParkingSpotService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ParkingSpotExpectedValuesTests {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private ParkingSpotService parkingSpotService;

    @Test
    @Tag("expected-values")
    @DisplayName("Expected Values: deve buscar a vaga A-03 com os valores esperados")
    void deveBuscarVagaA03ComValoresEsperados() {
        ParkingSpot result = parkingSpotRepository.findByBlockAndParkingSpotNumber("A", "03");

        assertNotNull(result);
        assertEquals("A", result.getBlock());
        assertEquals("03", result.getParkingSpotNumber());
        assertEquals("Marcos Oliveira", result.getResponsibleName());
        assertEquals(ParkingSpot.Status.RESERVED, result.getStatus());
    }

    @Test
    @Tag("expected-values")
    @DisplayName("Expected Values: deve retornar os dados esperados para o ID 3")
    void deveRetornarDadosEsperadosParaId3() {
        Optional<ParkingSpotDTO> result = parkingSpotService.getUserById(3L);

        assertTrue(result.isPresent());
        assertEquals("A", result.get().block());
        assertEquals("03", result.get().parkingSpotNumber());
        assertEquals("Marcos Oliveira", result.get().responsibleName());
    }

    @Test
    @Tag("expected-values")
    @DisplayName("Expected Values: deve validar que a placa HZT-7130 existe no banco")
    void deveValidarExistenciaDaPlacaHZT7130() {
        boolean exists = parkingSpotRepository.existsByCarPlateCar("HZT-7130");

        assertTrue(exists);
    }

    @Test
    @Tag("expected-values")
    @DisplayName("Expected Values: deve buscar Rafael Costa na vaga A-05")
    void deveBuscarRafaelCostaNaVagaA05() {
        ParkingSpot result = parkingSpotRepository.findByBlockAndParkingSpotNumber("A", "05");

        assertNotNull(result);
        assertEquals("A", result.getBlock());
        assertEquals("05", result.getParkingSpotNumber());
        assertEquals("Rafael Costa", result.getResponsibleName());
        assertEquals(ParkingSpot.Status.RESERVED, result.getStatus());
    }
}