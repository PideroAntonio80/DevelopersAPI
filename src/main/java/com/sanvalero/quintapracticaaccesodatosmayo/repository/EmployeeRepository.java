package com.sanvalero.quintapracticaaccesodatosmayo.repository;

import com.sanvalero.quintapracticaaccesodatosmayo.domain.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 18/05/2021
 */

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Set<Employee> findAll();
}
