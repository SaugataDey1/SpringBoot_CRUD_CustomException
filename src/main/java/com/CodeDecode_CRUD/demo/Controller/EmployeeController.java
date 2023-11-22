package com.CodeDecode_CRUD.demo.Controller;

import com.CodeDecode_CRUD.demo.Entity.Employee;
import com.CodeDecode_CRUD.demo.Exception.BusinessException;
import com.CodeDecode_CRUD.demo.Exception.ControllerException;
import com.CodeDecode_CRUD.demo.Service.EmployeeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/code")
public class EmployeeController
{
    @Autowired
    private EmployeeServiceInterface employeeServiceInterface;

    // To Create a Employee Entity.
    @PostMapping("/save")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee)
    {
        try 
        {
            Employee employeeSaved = employeeServiceInterface.addEmployee(employee);
            return new ResponseEntity<Employee>(employeeSaved, HttpStatus.CREATED);
        }
        catch (BusinessException e)
        {
            ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            ControllerException ce = new ControllerException("611","Something went wrong in Controller");
            return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
        }
    }


    // To get all the Employees
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees()
    {
        List<Employee> allEmployees = employeeServiceInterface.getAllEmployees();
        return new ResponseEntity<List<Employee>> (allEmployees, HttpStatus.OK);
    }


    // To get all the Employee by Id
    @GetMapping("/emp/{empId}")
    public ResponseEntity<?> getEmpById(@PathVariable ("empId") Long empId)
    {
        try
        {
            Employee empRetrieved = employeeServiceInterface.getEmpById(empId);
            return new ResponseEntity<Employee>(empRetrieved, HttpStatus.OK);
        }
        catch (BusinessException e)
        {
            ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            ControllerException ce = new ControllerException("612","Something went wrong in Controller");
            return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
        }
    }


    // To delete an employee of a particular Id
    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<Void> deleteEmpById(@PathVariable ("empId") Long empId)
    {
        employeeServiceInterface.deleteEmpById(empId);
        return new ResponseEntity<Void> (HttpStatus.ACCEPTED);
    }


    // To update the Employee entity
    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee)
    {
        Employee employeeSaved = employeeServiceInterface.addEmployee(employee);
        return new ResponseEntity<Employee>(employeeSaved, HttpStatus.CREATED);
    }
}
