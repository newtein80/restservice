package com.nile.apiservice.factory.service;

import java.util.List;

import javax.validation.Valid;

import com.nile.apiservice.factory.model.entity.Employee;
import com.nile.apiservice.factory.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

// @RequiredArgsConstructor
@Service
public class EmployeeService {
    
    @Autowired
    public EmployeeRepository employeeRepository;

    public EmployeeService() {
        super();
    }

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

    public Employee saveEmployee(Employee employee) {
        System.out.println("Before Persist");
        return this.employeeRepository.save(employee);
    }

    public int getTotalEmployeesCount() {
        return this.employeeRepository.NqgetTotalEmployees();
    }
    
    public int getTotalEmployeesCountProc() {
        return this.employeeRepository.ProcgetTotalEmployees();
    }

    public int getTotalEmployeeCountSingleOutput(int employeeid) {
        return this.employeeRepository.NqgetTotalEmployeesingleoutput(employeeid);
    }

    public int getTotalEmployeeCountSingleOutputProc(int employeeid) {
        return this.employeeRepository.ProcgetTotalEmployeesingleoutput(employeeid);
    }
}
