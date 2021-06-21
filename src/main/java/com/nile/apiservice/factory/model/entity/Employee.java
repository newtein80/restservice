package com.nile.apiservice.factory.model.entity;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(schema = "rest", name = "t_employee_info")
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int id;

    @Column(name = "employeename")
    @NotNull
    private String employeename;

    @Column(name = "department")
    @NotNull(message = "Department is required")
    @Pattern(regexp = "^[0-9A-Z]*$", message = "Department accepts only Alphanumeric value")
    @Size(min = 10, max = 50, message = "Department accepts only upto 50 character and minimum 10 characters")
    private String department;

    @Column(name = "joiningdate")
    @NotNull
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private String joiningdate;

    @Column(name = "age")
    @NotNull(message = "Age is required")
    @Min(value = 18, message = "The minimum age requirement is 18 ")
    private int age;

    @Column(name = "address")
    @NotNull
    private String address;

    @Column(name = "lefton")
    private ZonedDateTime lefton;

    @Column(name = "leftjob")
    @NotNull
    private boolean leftjob;

}
