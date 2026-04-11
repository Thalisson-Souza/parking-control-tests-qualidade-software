package com.api.parking_control.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "TB_PARKING_SPOT",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"block", "parking_spot_number"})
        }
)
public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parking_spot_number", nullable = false)
    @NotNull
    private String parkingSpotNumber;

    @Column(name = "block", nullable = false)
    @NotNull
    private String block;

    @OneToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private Car car;

    @Column(name = "date_register")
    private LocalDateTime registrationDate;

    @Column(name = "responsible_name", nullable = false)
    @NotNull
    private String responsibleName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        AVAILABLE,
        OCCUPIED,
        RESERVED
    }

    public ParkingSpot() {
    }

    public Status getStatus() {
        return status;
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

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
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
