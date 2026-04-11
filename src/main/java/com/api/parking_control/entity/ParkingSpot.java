package com.api.parking_control.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_PARKING_SPOT")
public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "parkingSpotNumber", unique = true, nullable = false)
    @NotNull
    private String parkingSpotNumber;

    @OneToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private Car car;

    @Column(name = "date_register")
    private LocalDateTime registrationDate;

    @Column(name = "responsibleName", unique = true,nullable = false)
    @NotNull
    private String responsibleName;

    @Column(name = "apartment", unique = true,nullable = false)
    @NotNull
    private String block;

    @Enumerated(EnumType.STRING)
    private Status status;

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setStatus(ch.qos.logback.core.status.Status status) {
    }

    public enum Status {
        AVAILABLE,
        OCCUPIED,
        RESERVED
    }

    public ParkingSpot() {
    }

    public String getBlock() {
        return block;
    }

    public Status getStatus() {
        return status;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public Car getCar() {
        return car;
    }

    public ParkingSpot(Status status) {
        this.status = status;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public void setParkingSpotNumber(String parkingSpotNumber) {
        this.parkingSpotNumber = parkingSpotNumber;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getResponsibleName() {
        return responsibleName;
    }

    public void setResponsibleName(String responsibleName) {
        this.responsibleName = responsibleName;
    }

    public Long getId() {
        return id;
    }
}
