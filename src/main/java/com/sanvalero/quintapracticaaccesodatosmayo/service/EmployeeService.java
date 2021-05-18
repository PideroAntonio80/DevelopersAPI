package com.sanvalero.quintapracticaaccesodatosmayo.service;

import com.sanvalero.quintapracticaaccesodatosmayo.domain.Employee;
import com.sanvalero.quintapracticaaccesodatosmayo.domain.dto.EmployeeDTO;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 18/05/2021
 */

public interface EmployeeService {

    Set<Employee> findAll();
    Optional<Employee> findById(long id);

    Employee addEmployeeToDepartment(long id, EmployeeDTO employeeDTO);
    Employee modifyEmployee(long id, EmployeeDTO employeeDTO);
    Employee modifyBySalary(long id, float salary);
    void deleteEmployee(long id);
}
