package com.nile.apiservice.factory.service;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import com.nile.apiservice.factory.model.dto.EmployeeResultSet;
import com.nile.apiservice.factory.model.entity.Employee;
import com.nile.apiservice.factory.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// @RequiredArgsConstructor
@Service
public class EmployeeService {
    
    @Autowired
    public EmployeeRepository employeeRepository;

    @PersistenceContext
    EntityManager entityManager;

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

    public Map<String, ?> getEmployeeInfoMultipleOutput(int employeeid) {
        return this.employeeRepository.NqgetEmployeeInfomultipleoutput(employeeid);
    }

    public Map<String, ?> getNamedProcEmpNameAndDept(int employeeid) {
        return this.employeeRepository.NamedProcGetEmpNameAndDept(employeeid);
    }

    public Map<String, ?> getEmployeeInfoTableSetOutput(int employeeid) {
        return this.employeeRepository.NqgetEmployeeInfotablesetoutput(employeeid);
    }

    public Map<String, ?> getNamedProcEmployeeInfoTableSetOutput(int employeeid) {
        return this.employeeRepository.NamedProcGetEmployeeInfotablesetoutput(employeeid);
    }

    public List<EmployeeResultSet> getEntityMngEmployeeInfo(int employeeid) {
        StoredProcedureQuery sProcedureQuery = entityManager.createNamedStoredProcedureQuery("emp.GetEmpNameAndDeptTableSet");
        sProcedureQuery.setParameter("employeeid", employeeid);
        sProcedureQuery.execute();
        // todo: 아래의 경고 문구 해결 방법?
        return sProcedureQuery.getResultList();
        
    }

    @Transactional
    public List<EmployeeResultSet> getEntityMngEmployeeInfoOutputCursor(int employeeid) {
        StoredProcedureQuery sProcedureQuery = entityManager.createNamedStoredProcedureQuery("emp.GetEmpNameAndDeptCursor");
        // sProcedureQuery.setParameter("employeeid", employeeid);
        sProcedureQuery.setParameter(2, employeeid); // * 위의 NamedStoredProcedure 선언된 곳의 param 순서에 맞게 set 하여도 된다.
        sProcedureQuery.execute();
        // todo: 아래의 경고 문구 해결 방법?
        return sProcedureQuery.getResultList();
        
    }
}
