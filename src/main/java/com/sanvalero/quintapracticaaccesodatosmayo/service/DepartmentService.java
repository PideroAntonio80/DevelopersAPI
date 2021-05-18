package com.sanvalero.quintapracticaaccesodatosmayo.service;

import com.sanvalero.quintapracticaaccesodatosmayo.domain.Department;
import com.sanvalero.quintapracticaaccesodatosmayo.domain.Employee;
import com.sanvalero.quintapracticaaccesodatosmayo.domain.dto.DepartmentDTO;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 18/05/2021
 */

public interface DepartmentService {

    Set<Department> findAll();
    Optional<Department> findById(long id);
    Set<Employee> findEmployeeBySalary(long id, float salary);

    Department addDepartment(Department department);
    Department modifyDepartment(long id, DepartmentDTO departmentDTO);
    void deleteDepartment(long id);
}
