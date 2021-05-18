package com.sanvalero.quintapracticaaccesodatosmayo.service;

import com.sanvalero.quintapracticaaccesodatosmayo.domain.Department;
import com.sanvalero.quintapracticaaccesodatosmayo.domain.Employee;
import com.sanvalero.quintapracticaaccesodatosmayo.domain.dto.DepartmentDTO;
import com.sanvalero.quintapracticaaccesodatosmayo.exception.DepartmentNotFoundException;
import com.sanvalero.quintapracticaaccesodatosmayo.repository.DepartmentRepository;
import com.sanvalero.quintapracticaaccesodatosmayo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Creado por @ author: Pedro Orós
 * el 18/05/2021
 */

@Service
public class DepartmentServiceImp implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Override
    public Set<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> findById(long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Set<Employee> findEmployeeBySalary(long id, float salary) {
        Department department = findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
        Set<Employee> myList =department.getEmployees().stream()
                .filter(employee -> employee.getSalary() >= salary)
                .collect(Collectors.toSet());
        return myList;
    }

    @Override
    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department modifyDepartment(long id, DepartmentDTO departmentDTO) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
        setDepartment(department, departmentDTO);
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
        departmentRepository.delete(department);
    }

    public void setDepartment(Department department, DepartmentDTO departmentDTO) {
        department.setName(departmentDTO.getName());
        department.setPermanent(departmentDTO.isPermanent());
    }
}
