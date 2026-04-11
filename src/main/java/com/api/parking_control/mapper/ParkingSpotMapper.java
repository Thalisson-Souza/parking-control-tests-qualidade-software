package com.api.parking_control.mapper;

import com.api.parking_control.dtos.CarDTO;
import com.api.parking_control.dtos.ParkingSpotDTO;
import com.api.parking_control.entity.Car;
import com.api.parking_control.entity.ParkingSpot;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ParkingSpotMapper {

    ParkingSpotDTO toDTO(ParkingSpot parkingSpot);
    ParkingSpot toEntity(ParkingSpotDTO parkingSpotDTO);
    Car toEntity(CarDTO carDTO);
    CarDTO toDTO(Car car);
    List<ParkingSpotDTO> toDTOList(List<ParkingSpot> parkingSpots);

}
