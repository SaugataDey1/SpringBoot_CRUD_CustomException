package com.CodeDecode_CRUD.demo.Repos;

import com.CodeDecode_CRUD.demo.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeCrudRepo extends JpaRepository<Employee, Long> {
}
