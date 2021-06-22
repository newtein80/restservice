package com.nile.apiservice.factory.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
// @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String modelname;
    private String model;
    private String fueltype;
    private String mileage;
    private String classification;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="manufacturer_id", insertable = false, updatable = false)
    private Manufacturer manufacturer;
}
