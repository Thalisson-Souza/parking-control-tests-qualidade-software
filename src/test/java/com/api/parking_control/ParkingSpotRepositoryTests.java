package com.api.parking_control;

import com.api.parking_control.entity.Car;
import com.api.parking_control.entity.ParkingSpot;
import com.api.parking_control.repository.CarRepository;
import com.api.parking_control.repository.ParkingSpotRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ParkingSpotRepositoryTests {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private CarRepository carRepository;

    @Test
    @Order(1)
    @Tag("repository")
    @DisplayName("Repository: existsByCarPlateCar deve validar placa existente")
    void existsByCarPlateCarDeveRetornarTrueParaPlacaExistente() {
        String plate = uniquePlate();
        createParkingSpot("A", uniqueSpot(), "Carlos Teste", plate);

        boolean exists = parkingSpotRepository.existsByCarPlateCar(plate);

        assertTrue(exists);
    }

    @Test
    @Order(2)
    @Tag("repository")
    @DisplayName("Repository: existsByBlockAndParkingSpotNumber deve validar vaga existente")
    void existsByBlockAndParkingSpotNumberDeveRetornarTrueParaVagaExistente() {
        String spot = uniqueSpot();
        createParkingSpot("A", spot, "Ana Teste", uniquePlate());

        boolean exists = parkingSpotRepository.existsByBlockAndParkingSpotNumber("A", spot);

        assertTrue(exists);
    }

    @Test
    @Order(3)
    @Tag("repository")
    @DisplayName("Repository: findByResponsibleName deve retornar vaga pelo nome")
    void findByResponsibleNameDeveRetornarRegistro() {
        String responsibleName = "Marcos Teste " + System.nanoTime();
        createParkingSpot("A", uniqueSpot(), responsibleName, uniquePlate());

        ParkingSpot result = parkingSpotRepository.findByResponsibleName(responsibleName);

        assertNotNull(result);
        assertEquals(responsibleName, result.getResponsibleName());
    }

    @Test
    @Order(4)
    @Tag("repository")
    @DisplayName("Repository: findByBlockAndParkingSpotNumber deve retornar vaga pelo numero")
    void findByBlockAndParkingSpotNumberDeveRetornarRegistro() {
        String spot = uniqueSpot();
        createParkingSpot("A", spot, "Joana Teste", uniquePlate());

        ParkingSpot result = parkingSpotRepository.findByBlockAndParkingSpotNumber("A", spot);

        assertNotNull(result);
        assertEquals("A", result.getBlock());
        assertEquals(spot, result.getParkingSpotNumber());
    }

    private void createParkingSpot(String block, String spotNumber, String responsibleName, String plate) {
        Car car = new Car();
        car.setPlateCar(plate);
        car.setModelCar("Civic");
        car.setColorCar("Preto");
        car = carRepository.save(car);

        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setBlock(block);
        parkingSpot.setParkingSpotNumber(spotNumber);
        parkingSpot.setResponsibleName(responsibleName);
        parkingSpot.setRegistrationDate(LocalDateTime.now());
        parkingSpot.setStatus(ParkingSpot.Status.RESERVED);
        parkingSpot.setCar(car);

        parkingSpotRepository.save(parkingSpot);
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

