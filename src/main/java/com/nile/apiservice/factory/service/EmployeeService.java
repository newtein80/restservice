package com.nile.apiservice.factory.service;

import java.util.List;

import com.nile.apiservice.factory.model.entity.Employee;
import com.nile.apiservice.factory.repository.EmployeeRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    
    private final EmployeeRepository employeeRepository;

    public List<Employee> saveAllEmployee(List<Employee> employees) {
        return this.employeeRepository.saveAll(employees);
    }

    public List<Employee> findAllEmployees() {
        return this.employeeRepository.findAll();
    }

    public List<Employee> findAllEmployeesByIds(List<Integer> ids) {
        return this.employeeRepository.findAllById(ids);
    }

    public Employee findEmployeeById(int id) {
        return this.employeeRepository.findById(id);
    }

    public List<Employee> findEmployeesByName(String employeename) {
        return this.employeeRepository.findByEmployeename(employeename);
    }
    
}
