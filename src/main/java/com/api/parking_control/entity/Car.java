package com.api.parking_control.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Entity
@Table(name = "TB_CAR")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "plateCar", nullable = false)
    @NotNull
    private String plateCar;

    @Column(name = "modelCar",nullable = false)
    @NotNull
    private String modelCar;

    @Column(name = "colorCar",nullable = false)
    @NotNull
    private String colorCar;


    public Car() {
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setPlateCar(String plateCar) {
        this.plateCar = plateCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }

    public void setColorCar(String colorCar) {
        this.colorCar = colorCar;
    }

    public String getPlateCar() {
        return plateCar;
    }

    public String getModelCar() {
        return modelCar;
    }

    public String getColorCar() {
        return colorCar;
    }
}
