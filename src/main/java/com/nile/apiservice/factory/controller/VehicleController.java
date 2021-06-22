package com.nile.apiservice.factory.controller;

import java.util.List;

import com.nile.apiservice.factory.model.entity.Manufacturer;
import com.nile.apiservice.factory.model.entity.Vehicle;
import com.nile.apiservice.factory.model.entity.vehicle.Car;
import com.nile.apiservice.factory.model.entity.vehicle.Motorbike;
import com.nile.apiservice.factory.repository.CarRepository;
import com.nile.apiservice.factory.repository.ManufacturerRepository;
import com.nile.apiservice.factory.repository.MotorbikeRepository;
import com.nile.apiservice.factory.repository.VehicleRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/nileapi/vehicle")
public class VehicleController {
    private final ManufacturerRepository manufacturerRepository;
    private final VehicleRepository vehicleRepository;
    private final CarRepository carRepository;
    private final MotorbikeRepository motorbikeRepository;

    @PostMapping("/addmanufacturers")
    public List<Manufacturer> addManufacturers(
        @RequestBody List<Manufacturer> manufacturers
    ) {
        return this.manufacturerRepository.saveAll(manufacturers);
    }

    @PostMapping("/addvehicles")
    public List<Vehicle> addVehicles(
        @RequestBody List<Vehicle> vehicles
    ) {
        return this.vehicleRepository.saveAll(vehicles);
    }

    @PostMapping("/addcars")
    public List<Car> addCars(
        @RequestBody List<Car> cars
    ) {
        return this.carRepository.saveAll(cars);
    }

    @PostMapping("/addmotorbikes")
    public List<Motorbike> addMotorbikes(
        @RequestBody List<Motorbike> motorbikes
    ) {
        return this.motorbikeRepository.saveAll(motorbikes);
    }

    @GetMapping("/getmanufacturers")
    public List<Manufacturer> getManufacturers() {
        return this.manufacturerRepository.findAll();
    }

    @GetMapping("/getvehicles")
    public List<Vehicle> getVehicles() {
        return this.vehicleRepository.findAll();
    }

    @GetMapping("/getcars")
    public List<Car> getCars() {
        return this.carRepository.findAll();
    }

    @GetMapping("/getmotorbikes")
    public List<Motorbike> getMotorbikes() {
        return this.motorbikeRepository.findAll();
    }
}
