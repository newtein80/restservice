package com.nile.apiservice.factory.controller;

import com.nile.apiservice.factory.model.entity.Doctor;
import com.nile.apiservice.factory.model.entity.Patient;
import com.nile.apiservice.factory.service.HospitalService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/nileapi/hostpital")
public class HospitalController {
    
    private final HospitalService hospitalService;

    @PostMapping("/adddoctor")
    public Doctor addDoctor(
        @RequestBody Doctor doctor
    ) {
        return this.hospitalService.addDoctor(doctor);
    }

    @PostMapping("/addpatient")
    public Patient addPatient(
        @RequestBody Patient patient
    ) {
        return this.hospitalService.addPatient(patient);
    }
}
