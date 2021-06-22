package com.nile.apiservice.factory.model.entity.sms;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.nile.apiservice.factory.model.entity.Sms;

@Entity
@DiscriminatorValue(value = "not null")
public class Other extends Sms{
    
}
