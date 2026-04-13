package com.api.parking_control.controllersTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ParkingSpotControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    @Tag("controller")
    @DisplayName("Controller: POST /parking-spot deve retornar 201 e payload da vaga")
    void createParkingSpotDeveRetornarCreatedEPayload() throws Exception {
        String spot = uniqueSpot();
        String plate = uniquePlate();
        String payload = """
                {
                  "block": "C",
                  "parkingSpotNumber": "%s",
                  "responsibleName": "Andre Teste",
                  "car": {
                    "plateCar": "%s",
                    "modelCar": "Onix",
                    "colorCar": "Branco"
                  }
                }
                """.formatted(spot, plate);

        mockMvc.perform(post("/parking-spot")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.block").value("C"))
                .andExpect(jsonPath("$.parkingSpotNumber").value(spot))
                .andExpect(jsonPath("$.responsibleName").value("Andre Teste"));
    }

    @Test
    @Order(2)
    @Tag("controller")
    @DisplayName("Controller: POST /parking-spot deve retornar 409 para dados duplicados")
    void createParkingSpotDuplicadoDeveRetornarConflict() throws Exception {
        String spot = uniqueSpot();
        String plate = uniquePlate();
        String payload = """
                {
                  "block": "C",
                  "parkingSpotNumber": "%s",
                  "responsibleName": "Andre Duplicado",
                  "car": {
                    "plateCar": "%s",
                    "modelCar": "HB20",
                    "colorCar": "Preto"
                  }
                }
                """.formatted(spot, plate);

        mockMvc.perform(post("/parking-spot")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload));

        mockMvc.perform(post("/parking-spot")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Ja existe um carro ou vaga cadastrada com esses dados."));
    }

    @Test
    @Order(3)
    @Tag("controller")
    @DisplayName("Controller: POST /parking-spot deve retornar 400 para erro de validacao")
    void createParkingSpotComPayloadInvalidoDeveRetornarBadRequest() throws Exception {
        String spot = uniqueSpot();
        String payloadInvalido = """
                {
                  "block": "D",
                  "parkingSpotNumber": "%s",
                  "responsibleName": "Andre Invalido",
                  "car": {
                    "plateCar": "PLACA-ERRADA",
                    "modelCar": "Argo",
                    "colorCar": "Cinza"
                  }
                }
                """.formatted(spot);

        mockMvc.perform(post("/parking-spot")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadInvalido))
                .andExpect(status().isBadRequest());
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




