package com.api.parking_control;

import com.api.parking_control.dtos.ParkingSpotDTO;
import com.api.parking_control.services.ParkingSpotService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ParkingControlApplicationTests {

	@Autowired
	private ParkingSpotService parkingSpotService;

	@Test
	@Order(1)
	@Tag("basico")
	@DisplayName("Verificar se o Spring carregou o Service")
	void contextLoads() {
		assertNotNull(parkingSpotService);
	}

	@Test
	@Order(2)
	@Tag("query")
	@DisplayName("Verificar se existem vagas cadastradas no banco")
	void testelistaNaoVazia() {
		List<ParkingSpotDTO> result = parkingSpotService.findAll();
		assertNotNull(result);
		assertFalse(result.isEmpty(), "A lista de vagas não podia estar vazia. existem registros no banco.");
	}

	@Test
	@Order(3)
	@Tag("Validacao")
	@DisplayName("Validar existência da placa (HZT-7130) no banco")
	void testeExistePorPlaca () {
		String placaExistente = "HZT-7130";

		boolean existe = parkingSpotService.existsByCarPlateCar(placaExistente);

		assertTrue(existe, "a placa " + placaExistente + "deveria existir no banco, mas não tem.");
	}

	@Test
	@Order(4)
	@Tag("query")
	@DisplayName("Testar busca de vaga inexistente por ID")
	void testeIdInexistente() {
		Optional<ParkingSpotDTO> result = parkingSpotService.getUserById(9999L);

		assertFalse(result.isPresent(), "Não deveria encontrar nada para o ID 9999");
	}

	@Test
	@Order(5)
	@Tag("query")
	@DisplayName("Buscar vaga por nome Marcos Oliveira, responsável por um estacionamento no banco.")
	void testeBuscaPorNome() {
		String nomeResponsavel = "Marcos Oliveira";

		ParkingSpotDTO result = parkingSpotService.getNameParkingSpot(nomeResponsavel);

		assertNotNull(result, "Deveria encontrar a vaga do responsável: " + nomeResponsavel);
		assertEquals(nomeResponsavel, result.responsibleName());
	}


}
