package com.api.parking_control.services;

import com.api.parking_control.dtos.ParkingSpotDTO;
import com.api.parking_control.entity.ParkingSpot;
import com.api.parking_control.mapper.ParkingSpotMapper;
import com.api.parking_control.mapper.ParkingUpdate;
import com.api.parking_control.repository.CarRepository;
import com.api.parking_control.repository.ParkingSpotRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class    ParkingSpotService {

    private final ParkingSpotRepository parkingSpotRepository;
    private final CarRepository carRepository;
    private final ParkingSpotMapper parkingSpotMapper;
    private final ParkingUpdate parkingUpdate;

    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository, CarRepository carRepository, ParkingSpotMapper parkingSpotMapper, ParkingUpdate parkingUpdate) {
        this.parkingSpotRepository = parkingSpotRepository;
        this.carRepository = carRepository;
        this.parkingSpotMapper = parkingSpotMapper;
        this.parkingUpdate = parkingUpdate;
    }

    @Transactional
    public ParkingSpot createParkingSpot(ParkingSpotDTO parkingSpotDTO){
        if(existsByCarPlateCar(parkingSpotDTO.car().plateCar())){
            return null;
        }
        if(existsByParkingSpotNumber(parkingSpotDTO.parkingSpotNumber())){
            return null;
        }
        if(existsByBlock(parkingSpotDTO.block())){
            return null;
        }

        var car = parkingSpotMapper.toEntity(parkingSpotDTO.car());
        car = carRepository.save(car);

        var parkingSpot = parkingSpotMapper.toEntity(parkingSpotDTO);
        parkingSpot.setRegistrationDate(LocalDateTime.now());
        parkingSpot.setCar(car);

        parkingSpot.setStatus(ParkingSpot.Status.RESERVED);

        return parkingSpotRepository.save(parkingSpot);
    }

    public boolean existsByCarPlateCar(@NotBlank String licensePlateCar){
        return parkingSpotRepository.existsByCarPlateCar(licensePlateCar);
    }

    public boolean existsByParkingSpotNumber(@NotBlank String spotNumber) {
        return parkingSpotRepository.existsByParkingSpotNumber(spotNumber);
    }

    public boolean existsByBlock(@NotBlank String blockSpotId) {
        return parkingSpotRepository.existsByBlock(blockSpotId);
    }

    public List<ParkingSpotDTO> findAll() {
        return parkingSpotMapper.toDTOList(parkingSpotRepository.findAll());
    }

    public Optional<ParkingSpotDTO> getUserById(Long id) {
        Optional <ParkingSpot> parkingSpot = parkingSpotRepository.findById(id);
        return parkingSpot.map(parking -> parkingSpotMapper.toDTO(parking));
    }

    public void deleteById(Long id){
        var parkingExists = parkingSpotRepository.existsById(id);

        if(parkingExists){
            parkingSpotRepository.deleteById(id);
        }
    }

    public ParkingSpotDTO updateParkingSpot(ParkingSpotDTO parkingSpotDTO, Long id) {
        ParkingSpot parkingSpot = parkingSpotRepository.findById(id).orElseThrow();
        parkingUpdate.updateParkingSpot(parkingSpotDTO, parkingSpot);
        return parkingSpotMapper.toDTO(parkingSpotRepository.save(parkingSpot));
    }

    public ParkingSpotDTO getNameParkingSpot(String name) {
        return parkingSpotMapper.toDTO(parkingSpotRepository.findByResponsibleName(name));
    }

    public Object getSpotNumber(String spotNumber) {
        return parkingSpotMapper.toDTO(parkingSpotRepository.findByParkingSpotNumber(spotNumber));
    }
}
