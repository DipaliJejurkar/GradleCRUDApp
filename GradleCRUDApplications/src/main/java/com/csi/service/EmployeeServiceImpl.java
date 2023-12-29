package com.csi.service;

import com.csi.entity.Employee;
import com.csi.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl {

    @Autowired
    EmployeeRepo employeeRepo;

    public Employee signUp(Employee employee){
        return employeeRepo.save(employee);
    }

  public Optional<Employee> findById(int empId){
        return employeeRepo.findById(empId);
  }
  public List<Employee> findAll(){
        return employeeRepo.findAll();
  }

  public Employee update(Employee employee){
        return employeeRepo.save(employee);
  }

  public void deleteById(int empId){
        employeeRepo.deleteById(empId);
  }

  public void deleteAll(){
        employeeRepo.deleteAll();
  }
}
