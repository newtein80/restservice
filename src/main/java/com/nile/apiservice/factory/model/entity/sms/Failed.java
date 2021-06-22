package com.nile.apiservice.factory.model.entity.sms;

import javax.persistence.Entity;

import com.nile.apiservice.factory.model.entity.Sms;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Failed extends Sms{
    private String errormessage;
}
