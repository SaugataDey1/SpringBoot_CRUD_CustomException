package com.CodeDecode_CRUD.demo.Service;

import com.CodeDecode_CRUD.demo.Entity.Employee;
import com.CodeDecode_CRUD.demo.Exception.BusinessException;
import com.CodeDecode_CRUD.demo.Repos.EmployeeCrudRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeService implements EmployeeServiceInterface
{
    @Autowired
    private EmployeeCrudRepo crudRepo;

    @Override
    public Employee addEmployee(Employee employee)
    {
        if(employee.getName().isEmpty() || employee.getName().length() == 0)
        {
            throw new BusinessException("601","Please share proper name, it is blank");
        }

        try
        {
            Employee savedEmployee = crudRepo.save(employee);
            return savedEmployee;
        }
        catch (IllegalArgumentException e)
        {
            throw new BusinessException("602","given employee is null" + e.getMessage());
        }
        catch (Exception e)
        {
            throw new BusinessException("603","Something went wrong in Service Layer while saving the employees : " + e.getMessage());
        }
    }

    @Override
    public List<Employee> getAllEmployees()
    {
        List<Employee> empList = null;

        try
        {
            empList = crudRepo.findAll();
        }
        catch (Exception e)
        {
            throw new BusinessException("605","Something went wrong in Service Layer while facing all employees : " + e.getMessage());
        }

        if(empList.isEmpty())
            throw new BusinessException("604", "Hey List is completely empty, we have nothing to return");
        return empList;

    }

    @Override
    public Employee getEmpById(Long empId)
    {
        try
        {
            return crudRepo.findById(empId).get();
        }
        catch (IllegalArgumentException e)
        {
            throw new BusinessException("606","given employee Id is null, please send some id to be searched : " + e.getMessage());
        }
        catch (NoSuchElementException e)
        {
            throw new BusinessException("607","given employee Id does not exist in database : " + e.getMessage());
        }
    }

    @Override
    public void deleteEmpById(Long empId)
    {
        try {
            crudRepo.deleteById(empId);
        }
        catch (IllegalArgumentException e)
        {
            throw new BusinessException("608","given employee Id is null, please send some id to be searched" + e.getMessage());
        }

    }
}
