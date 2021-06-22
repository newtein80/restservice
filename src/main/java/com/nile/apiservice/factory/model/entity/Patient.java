package com.nile.apiservice.factory.model.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.nile.apiservice.factory.model.base.PersonCommonInfo;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(schema = "rest", name = "t_patient_info")
@AttributeOverride(name = "entrydate", column = @Column(name ="visitdate"))
public class Patient extends PersonCommonInfo {
    private String symptom;
    private String healthhistory;
}
