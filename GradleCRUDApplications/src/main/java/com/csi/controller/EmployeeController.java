package com.csi.controller;

import com.csi.entity.Employee;
import com.csi.exception.RecordNotFoundException;
import com.csi.service.EmployeeServiceImpl;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;


import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Tag(name = "EMPLOYEE MANAGEMENT APPLICATION", description = "Employee Controller")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeService;

    @Operation(summary = " Employee HRM Application", description = "signup api",
            responses = {
                    @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = Employee.class))),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = LinkedHashMap.class)))})
    @PostMapping("/signup")
    public ResponseEntity<Employee> signUp(@Valid @RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.signUp(employee), HttpStatus.CREATED);
    }



    @Operation(summary = " Employee HRM Application", description = "signin api")
    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String empEmailId,@PathVariable String empPassword){
        boolean flag=false;
      for(Employee employee: employeeService.findAll()){
          flag=true;
      }return ResponseEntity.ok(flag);
    }

    @GetMapping ("findbyid/{empId}")
    public ResponseEntity<Optional<Employee>> findById(@PathVariable int empId){
        return ResponseEntity.ok(employeeService.findById(empId));

    }
    @GetMapping ("findall")
    public ResponseEntity<List<Employee>> findAll(){
        return ResponseEntity.ok(employeeService.findAll());

    }

    @PutMapping ("update")
    public ResponseEntity<Employee> update(@RequestParam int empId,@Valid @RequestBody Employee employee){
        Employee employee1=employeeService.findById(empId).orElseThrow(()->new RecordNotFoundException("ID NOT FOUND"));
        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee1.setEmpPassword(employee.getEmpPassword());

        return ResponseEntity.ok(employeeService.update(employee1));
    }

    @DeleteMapping("deletebyid/{empId}")
    public ResponseEntity<String> deleteById(@PathVariable int empId){
        employeeService.deleteById(empId);
        return ResponseEntity.ok("deleted");
    }

    @DeleteMapping("deleteall")
    public ResponseEntity<String> deleteAll(){
        employeeService.deleteAll();
        return ResponseEntity.ok("deleted ALL");
    }
}
