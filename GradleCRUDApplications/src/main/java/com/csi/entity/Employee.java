package com.csi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity   //help us to map our domain objects (POJOs) to the relational database tables.
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

private int empId;

    @Size(min=2,message = "Employee name should have atleast 2 digit")
    private String empName;


    private String empAddress;

    private long empContactNo;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date empDOB;

    @Email(message  ="Enter correct email id")
    private  String empEmailId;

    @Size(min = 4,message = "password should have atleast 4 digit")
    private String empPassword;

}
