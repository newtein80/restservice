package com.nile.apiservice.factory.model.entity;

import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nile.apiservice.factory.model.dto.EmployeeResultSet;

@Entity
@Table(schema = "rest", name = "t_employee_info")
@SqlResultSetMapping(
    name = "defEmployeeResultSet", classes = {
        @ConstructorResult(targetClass = EmployeeResultSet.class,
            columns = {
                @ColumnResult(name = "test_name"),
                @ColumnResult(name = "test_desc")
            }
        )
    }
)
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(name = "emp.GetEmpNameAndDept", procedureName = "getprocmultipleoutput",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "employeeid", type = Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.OUT, name = "test_name", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.OUT, name = "test_desc", type = String.class)
        }
    ),
    @NamedStoredProcedureQuery(name = "emp.GetEmpNameAndDeptTableSet", procedureName = "getproctablesetoutput", resultSetMappings = {"defEmployeeResultSet"},
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "employeeid", type = Integer.class)
        }
    ),
    @NamedStoredProcedureQuery(name = "emp.GetEmpNameAndDeptCursor", procedureName = "getproccursoroutput", resultClasses = {Employee.class},
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "employeeid", type = Integer.class), // * name property??? ?????? ?????? parameters ??? ????????? ????????? ?????? set????????? ??????.
            @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "v_ref_cursor", type = void.class)
        }
    )
})
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
    private Date joiningdate; // ! @Temporal should only be set on a java.util.Date or java.util.Calendar property --> @Temporal annotation??? Date ???????????? ??????????????????.

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
