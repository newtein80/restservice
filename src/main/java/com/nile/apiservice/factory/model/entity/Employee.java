package com.nile.apiservice.factory.model.entity;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "rest", name = "t_employee_info")
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int id;

    @Column(name = "employeename")
    private String employeename;

    @Column(name = "department")
    private String department;

    @Column(name = "regdate")
    private String regdate;

    @Column(name = "age")
    private int age;

    @Column(name = "address")
    private String address;

    @Column(name = "lefton")
    private ZonedDateTime lefton;

    @Column(name = "leftjob")
    private boolean leftjob;

}
