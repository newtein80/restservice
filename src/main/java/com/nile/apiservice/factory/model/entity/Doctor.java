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
@Table(schema = "rest", name = "t_doctor_info")
@AttributeOverride(name = "entrydate", column = @Column(name = "joiningdate"))
public class Doctor extends PersonCommonInfo{
    private String roomno;
    private String major;
}
