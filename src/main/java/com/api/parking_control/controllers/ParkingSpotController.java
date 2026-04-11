package com.api.parking_control.controllers;

import com.api.parking_control.dtos.ParkingSpotDTO;
import com.api.parking_control.entity.ParkingSpot;
import com.api.parking_control.mapper.ParkingSpotMapper;
import com.api.parking_control.repository.CarRepository;
import com.api.parking_control.services.ParkingSpotService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    private final ParkingSpotService parkingSpotService;
    private final CarRepository carRepository;
    private final ParkingSpotMapper parkingSpotMapper;


    public ParkingSpotController(ParkingSpotService parkingSpotService, CarRepository carRepository, ParkingSpotMapper parkingSpotMapper) {
        this.parkingSpotService = parkingSpotService;
        this.carRepository = carRepository;
        this.parkingSpotMapper = parkingSpotMapper;
    }

    @PostMapping
    public ResponseEntity<Object> createParkingSpot(@RequestBody @Valid ParkingSpotDTO parkingSpotDTO){
        ParkingSpot parkingSpot = parkingSpotService.createParkingSpot(parkingSpotDTO);

        if(parkingSpot == null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                    "status", HttpStatus.CONFLICT.value(),
                    "error", HttpStatus.CONFLICT.getReasonPhrase(),
                    "message", "Ja existe um carro ou vaga cadastrada com esses dados."
            ));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpot);
    }

    @GetMapping
    public ResponseEntity <List<ParkingSpotDTO>> getParkingSpotAll(){
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity <Object> getOneParkingSpot(@PathVariable(value = "id") Long id){
        Optional<ParkingSpotDTO> parkingId = parkingSpotService.getUserById(id);
        if(!parkingId.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status", HttpStatus.NOT_FOUND.value(),
                    "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
                    "message", "Id nao encontrado."
            ));
        }
        return ResponseEntity.status(HttpStatus.OK).body(parkingId.get());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity <Object> getNameParkingSpot(@PathVariable(value = "name") String name){
        ParkingSpotDTO parkingSpot = parkingSpotService.getNameParkingSpot(name);

        if(parkingSpot == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status", HttpStatus.NOT_FOUND.value(),
                    "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
                    "message", "Responsavel nao encontrado."
            ));
        }

        return ResponseEntity.ok(parkingSpot);
    }

    @GetMapping("/by-number/{spot-number}")
    public ResponseEntity <Object> getParkingSpotByNumber(@PathVariable(value = "spot-number") String spotNumber){
        Object parkingSpot = parkingSpotService.getSpotNumber(spotNumber);

        if(parkingSpot == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "status", HttpStatus.BAD_REQUEST.value(),
                    "error", HttpStatus.BAD_REQUEST.getReasonPhrase(),
                    "message", "Numero da vaga invalido."
            ));
        }

        return ResponseEntity.ok(parkingSpot);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot(@RequestBody @Valid ParkingSpotDTO parkingSpotDTO, @PathVariable(value = "id") Long id){
        return ResponseEntity.ok(parkingSpotService.updateParkingSpot(parkingSpotDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> deleteParkingSpot(@PathVariable(value = "id") Long id){
        parkingSpotService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
