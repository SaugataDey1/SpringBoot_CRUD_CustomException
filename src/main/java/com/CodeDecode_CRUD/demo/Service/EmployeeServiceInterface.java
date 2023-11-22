package com.CodeDecode_CRUD.demo.Service;

import com.CodeDecode_CRUD.demo.Entity.Employee;

import java.util.List;

public interface EmployeeServiceInterface
{
   public Employee addEmployee(Employee employee);

   public List<Employee> getAllEmployees();

   Employee getEmpById(Long empId);

   public void deleteEmpById(Long empId);
}
