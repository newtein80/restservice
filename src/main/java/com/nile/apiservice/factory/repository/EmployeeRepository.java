package com.nile.apiservice.factory.repository;

import java.util.Date;
import java.util.List;

import com.nile.apiservice.factory.model.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    Employee findById(int id);
    List<Employee> findByEmployeename(String employeename);
    List<Employee> findByDepartmentAndAgeLessThan(String department, int age);
    List<Employee> findByDepartmentAndAgeLessThanEqual(String department, int age);
    List<Employee> findByDepartmentAndAgeGreaterThanEqual(String department, int age);

    List<Employee> findByEmployeenameStartingWith(String employeename);
    List<Employee> findByEmployeenameContaining(String employeename);
    List<Employee> findByEmployeenameEndingWith(String employeename);

    List<Employee> findTop5ByAge(int age);
    List<Employee> findByAgeBetween(int startage, int endage);
    List<Employee> findByAgeIn(List<Integer> agegroup);

    List<Employee> findByJoiningdateAfter(Date date);
    List<Employee> findByJoiningdateBefore(Date date);
    List<Employee> findByJoiningdateBetween(Date startdate, Date enddate);
    List<Employee> findByJoiningdateBetweenAndDepartment(Date startdate, Date enddate, String department);

    List<Employee> findByLeftonIsNull();

    List<Employee> findByEmployeenameEquals(String employeename);
    List<Employee> findByEmployeenameIsNot(String employeename);
    List<Employee> findByEmployeenameIsNull();
    List<Employee> findByEmployeenameIsNotNull();

    List<Employee> findByEmployeenameOrderByJoiningdateAsc(String employeename);
    List<Employee> findByEmployeenameOrderByJoiningdateDesc(String employeename);

    List<Employee> findByLeftjobTrue();
    List<Employee> findByLeftjobFalse();
    List<Employee> findByLeftjob(boolean leftjob);

    @Query(value = "select gettotaldetailcount()", nativeQuery = true)
    int NqgetTotalEmployees();

    @Procedure(procedureName = "gettotaldetailcount")
    int ProcgetTotalEmployees();

    @Query(value = "select getprocsingleoutput(?1)", nativeQuery = true)
    int NqgetTotalEmployeesingleoutput(int employeeid);

    @Procedure(procedureName = "getprocsingleoutput")
    int ProcgetTotalEmployeesingleoutput(int employeeid);

}
