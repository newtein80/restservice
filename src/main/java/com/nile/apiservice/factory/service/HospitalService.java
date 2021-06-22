package com.nile.apiservice.factory.service;

import com.nile.apiservice.factory.model.entity.Doctor;
import com.nile.apiservice.factory.model.entity.Patient;
import com.nile.apiservice.factory.repository.DoctorRepository;
import com.nile.apiservice.factory.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalService {
    @Autowired DoctorRepository doctorRepository;
    @Autowired PatientRepository patientRepository;

    public HospitalService() {
        super();
    }

    public Doctor addDoctor(Doctor doctor) {
        return this.doctorRepository.save(doctor);
    }

    public Patient addPatient(Patient patient) {
        return this.patientRepository.save(patient);
    }
}
