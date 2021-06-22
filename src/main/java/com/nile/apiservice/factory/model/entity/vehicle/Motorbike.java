package com.nile.apiservice.factory.model.entity.vehicle;

import javax.persistence.Entity;

import com.nile.apiservice.factory.model.entity.Vehicle;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Motorbike extends Vehicle{
    
    private String pillionseat;
    private String passengerfootrest;
    private String saddleheight;
}
