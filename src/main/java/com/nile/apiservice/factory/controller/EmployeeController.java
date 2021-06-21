package com.nile.apiservice.factory.controller;

import java.util.List;

import com.nile.apiservice.factory.model.entity.Employee;
import com.nile.apiservice.factory.repository.EmployeeRepository;
import com.nile.apiservice.factory.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/nileapi/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    
    @Autowired EmployeeRepository employeeRepository;
    
    @PostMapping("/addemployees")
    public List<Employee> addAllEmployee(
        @RequestBody List<Employee> employees
    ) {
        return this.employeeService.saveAllEmployee(employees);
    }

    @GetMapping("/allemployees")
    public List<Employee> allEmployees() {
        return this.employeeService.findAllEmployees();
    }

    @GetMapping("/employeewithname")
    public List<Employee> getAllEmployeesWithName(
        @RequestParam String employeename
    ) {
        return this.employeeService.findEmployeesByName(employeename);
    }

    @GetMapping("/employeebyid")
    public Employee getEmployeeById(
        @RequestParam int id
    ) {
        return this.employeeService.findEmployeeById(id);
    }

    @GetMapping("/employeebyids")
    public List<Employee> getEmployeeByIds(
        @RequestBody List<Integer> ids
    ) {
        return this.employeeService.findAllEmployeesByIds(ids);
    }

    @GetMapping("/employeebydeptandageless")
    public List<Employee> getEmployeeByDepartmentAndAgeLess(
        @RequestParam String department,
        @RequestParam int age
    ) {
        return this.employeeRepository.findByDepartmentAndAgeLessThan(department, age);
    }

    @GetMapping("/employeebydeptandagelessequal")
    public List<Employee> getEmployeeByDepartmentAndAgeLessEqual(
        @RequestParam String department,
        @RequestParam int age
    ) {
        return this.employeeRepository.findByDepartmentAndAgeLessThanEqual(department, age);
    }

    @GetMapping("/employeebydeptandagegreaterequal")
    public List<Employee> getEmployeeByDepartmentAndAgeGreaterEqual(
        @RequestParam String department,
        @RequestParam int age
    ) {
        return this.employeeRepository.findByDepartmentAndAgeGreaterThanEqual(department, age);
    }

    @GetMapping("/employeebystartingwith")
    public List<Employee> getEmployeenameStartingWith(
        @RequestParam String employeename
    ) {
        return this.employeeRepository.findByEmployeenameStartingWith(employeename);
    }

    @GetMapping("/employeebycontaining")
    public List<Employee> getEmployeenameContaining(
        @RequestParam String employeename
    ) {
        return this.employeeRepository.findByEmployeenameContaining(employeename);
    }

    @GetMapping("/employeebyendingwith")
    public List<Employee> getEmployeenameEndingWith(
        @RequestParam String employeename
    ) {
        return this.employeeRepository.findByEmployeenameEndingWith(employeename);
    }
}
