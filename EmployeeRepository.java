package com.example.EmployeeCrudOperations.Repository;

import com.example.EmployeeCrudOperations.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee , Long> {

}
