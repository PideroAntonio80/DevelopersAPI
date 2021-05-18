package com.sanvalero.quintapracticaaccesodatosmayo.service;

import com.sanvalero.quintapracticaaccesodatosmayo.domain.Department;
import com.sanvalero.quintapracticaaccesodatosmayo.domain.Employee;
import com.sanvalero.quintapracticaaccesodatosmayo.domain.dto.EmployeeDTO;
import com.sanvalero.quintapracticaaccesodatosmayo.exception.DepartmentNotFoundException;
import com.sanvalero.quintapracticaaccesodatosmayo.exception.EmployeeNotFoundException;
import com.sanvalero.quintapracticaaccesodatosmayo.repository.DepartmentRepository;
import com.sanvalero.quintapracticaaccesodatosmayo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 18/05/2021
 */

@Service
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;
    
    @Override
    public Set<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findById(long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee addEmployeeToDepartment(long id, EmployeeDTO employeeDTO) {
        Employee newEmployee = new Employee();
        setEmployee(newEmployee, employeeDTO);
        newEmployee = employeeRepository.save(newEmployee);
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
        newEmployee.setDepartment(department);

        employeeRepository.save(newEmployee);

        return newEmployee;
    }

    @Override
    public Employee modifyEmployee(long id, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        setEmployee(employee, employeeDTO);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee modifyBySalary(long id, float salary) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        employee.setSalary(salary);
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        employeeRepository.delete(employee);
    }

    public void setEmployee(Employee employee, EmployeeDTO employeeDTO) {
        employee.setName(employeeDTO.getName());
        employee.setContractDate(employeeDTO.getContractDate());
        employee.setSalary(employeeDTO.getSalary());
    }
}
