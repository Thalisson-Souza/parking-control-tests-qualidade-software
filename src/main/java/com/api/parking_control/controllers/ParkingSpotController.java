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
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict");
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
            return ResponseEntity.status(HttpStatus.FOUND).body("Id not present!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(parkingId.get());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity <Object> getNameParkingSpot(@PathVariable(value = "name") String name){
        return ResponseEntity.ok(parkingSpotService.getNameParkingSpot(name));
    }

    @GetMapping("/by-number/{spot-number}")
    public ResponseEntity <Object> getParkingSpotByNumber(@PathVariable(value = "spot-number") String spotNumber){
        return ResponseEntity.ok(parkingSpotService.getSpotNumber(spotNumber));
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
