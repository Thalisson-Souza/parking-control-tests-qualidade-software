package com.api.parking_control.mapper;

import com.api.parking_control.dtos.ParkingSpotDTO;
import com.api.parking_control.entity.ParkingSpot;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ParkingUpdate {

    void updateParkingSpot(ParkingSpotDTO parkingSpotDTO, @MappingTarget ParkingSpot parkingSpot);
}
