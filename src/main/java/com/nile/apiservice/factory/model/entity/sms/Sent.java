package com.nile.apiservice.factory.model.entity.sms;

import java.time.ZonedDateTime;

import javax.persistence.Entity;

import com.nile.apiservice.factory.model.entity.Sms;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Sent extends Sms{
    private ZonedDateTime deliveredon;
}
