package com.api.parking_control;

import com.api.parking_control.dtos.CarDTO;
import com.api.parking_control.dtos.ParkingSpotDTO;
import com.api.parking_control.entity.ParkingSpot;
import com.api.parking_control.repository.ParkingSpotRepository;
import com.api.parking_control.services.ParkingSpotService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ParkingSpotServiceTests {

    @Autowired
    private ParkingSpotService parkingSpotService;

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Test
    @Order(1)
    @Tag("service")
    @DisplayName("Service: createParkingSpot deve criar vaga com status RESERVED")
    void createParkingSpotDeveCriar() {
        String spot = uniqueSpot();
        String plate = uniquePlate();
        ParkingSpotDTO dto = buildDto("B", spot, "Mauro Lima", plate);

        ParkingSpot result = parkingSpotService.createParkingSpot(dto);

        assertNotNull(result, "A vaga nao foi criada (possivel conflito de dados)");
        assertNotNull(result.getId());
        assertEquals(ParkingSpot.Status.RESERVED, result.getStatus());
        assertNotNull(result.getRegistrationDate());
        assertTrue(parkingSpotService.existsByCarPlateCar(plate));
    }

    @Test
    @Order(2)
    @Tag("service")
    @DisplayName("Service: updateParkingSpot deve atualizar os dados da vaga")
    void updateParkingSpotDeveAtualizar() {
        String spot = uniqueSpot();
        String plate = uniquePlate();
        ParkingSpotDTO dtoOriginal = buildDto("B", spot, "Joao Silva", plate);
        parkingSpotService.createParkingSpot(dtoOriginal);

        ParkingSpot created = parkingSpotRepository.findByBlockAndParkingSpotNumber("B", spot);
        assertNotNull(created);

        ParkingSpotDTO dtoAtualizado = buildDto("B", spot, "Joao Silva Atualizado", plate);
        ParkingSpotDTO atualizado = parkingSpotService.updateParkingSpot(dtoAtualizado, created.getId());

        assertNotNull(atualizado);
        assertEquals("Joao Silva Atualizado", atualizado.responsibleName());
    }

    @Test
    @Order(3)
    @Tag("service")
    @DisplayName("Service: deleteById deve remover vaga existente")
    void deleteByIdDeveRemover() {
        String spot = uniqueSpot();
        String plate = uniquePlate();
        ParkingSpotDTO dto = buildDto("B", spot, "Carla Mendes", plate);
        parkingSpotService.createParkingSpot(dto);

        ParkingSpot created = parkingSpotRepository.findByBlockAndParkingSpotNumber("B", spot);
        assertNotNull(created);

        parkingSpotService.deleteById(created.getId());

        assertFalse(parkingSpotRepository.existsById(created.getId()));
    }

    private ParkingSpotDTO buildDto(String block, String spot, String name, String plate) {
        return new ParkingSpotDTO(block, spot, name, new CarDTO(plate, "Civic", "Preto"));
    }

    private String uniquePlate() {
        long value = Math.abs(System.nanoTime() % 9000) + 1000;
        return "TST-" + value;
    }

    private String uniqueSpot() {
        long value = Math.abs(System.nanoTime() % 900000);
        return "S" + value;
    }
}

