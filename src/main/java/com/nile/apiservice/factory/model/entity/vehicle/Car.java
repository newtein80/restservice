package com.nile.apiservice.factory.model.entity.vehicle;

import javax.persistence.Entity;

import com.nile.apiservice.factory.model.entity.Vehicle;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Car extends Vehicle {
    private String seatingcapacity;
    private String sunroof;
}
