package com.sanvalero.quintapracticaaccesodatosmayo.controller;

import com.sanvalero.quintapracticaaccesodatosmayo.domain.Employee;
import com.sanvalero.quintapracticaaccesodatosmayo.domain.dto.EmployeeDTO;
import com.sanvalero.quintapracticaaccesodatosmayo.exception.EmployeeNotFoundException;
import com.sanvalero.quintapracticaaccesodatosmayo.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.sanvalero.quintapracticaaccesodatosmayo.controller.Response.NOT_FOUND;

/**
 * Creado por @ author: Pedro Orós
 * el 18/05/2021
 */

@RestController
@Tag(name = "Employees", description = "Employees information")
public class EmployeeController {

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    /*--------------------------------FIND_ALL----------------------------------*/
    @Operation(summary = "Get al employees list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employees list",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Employee.class)))),
            @ApiResponse(responseCode = "404", description = "Employees list failed",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/developers/employees", produces = "application/json")
    public ResponseEntity<Set<Employee>> getEmployees() {

        logger.info("Init getEmployees");

        Set<Employee> employees = employeeService.findAll();

        logger.info("End getEmployees");

        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }

    /*--------------------------------FIND_BY_ID----------------------------------*/
    @Operation(summary = "Get employee by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee exist",
                    content = @Content(schema = @Schema(implementation = Employee.class))),
            @ApiResponse(responseCode = "404", description = "Employee doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/developers/employees/{id}", produces = "application/json")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {

        logger.info("Init getEmployeeById");

        Employee employee = employeeService.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        logger.info("End getEmployeeById");

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    /*--------------------------------ADD----------------------------------*/
    @Operation(summary = "Add new employee into a department")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee added",
                    content = @Content(schema = @Schema(implementation = Employee.class))),
            @ApiResponse(responseCode = "404", description = "Employee couldn't be added",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value = "/developers/departments/{id}/employee", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Employee> addEmployee(@PathVariable long id, @RequestBody EmployeeDTO employeeDTO) {

        logger.info("Init addEmployee");

        Employee addedEmployee = employeeService.addEmployeeToDepartment(id, employeeDTO);

        logger.info("End addEmployee");

        return new ResponseEntity<>(addedEmployee, HttpStatus.CREATED);
    }

    /*--------------------------------MODIFY----------------------------------*/
    @Operation(summary = "Modify employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee modified",
                    content = @Content(schema = @Schema(implementation = Employee.class))),
            @ApiResponse(responseCode = "404", description = "Employee doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/developers/employees/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Employee> modifyEmployee(@PathVariable long id, @RequestBody EmployeeDTO employeeDTO) {

        logger.info("Init modifyEmployee");

        Employee employee = employeeService.modifyEmployee(id, employeeDTO);

        logger.info("End modifyEmployee");

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    /*--------------------------------MODIFY_BY_SALARY----------------------------------*/
    @Operation(summary = "Modify employee by salary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee salary modified",
                    content = @Content(schema = @Schema(implementation = Employee.class))),
            @ApiResponse(responseCode = "404", description = "Employee doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PatchMapping(value = "/developers/employees/{id}/salary", produces = "application/json")
    public ResponseEntity<Employee> modifyEmployeeBySalary(@PathVariable long id,
                                                          @RequestParam(value = "salary", defaultValue = "") float salary) {

        logger.info("Init modifyEmployeeBySalary");

        Employee employee = employeeService.modifyBySalary(id, salary);

        logger.info("End modifyEmployeeBySalary");

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    /*--------------------------------MODIFY_BY_DEPARTMENT----------------------------------*/
    /*@Operation(summary = "Modify employee by department")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee department modified",
                    content = @Content(schema = @Schema(implementation = Employee.class))),
            @ApiResponse(responseCode = "404", description = "Employee doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PatchMapping(value = "/developers/employees/{id}/department", produces = "application/json")
    public ResponseEntity<Employee> modifyEmployeeByDepartment(@PathVariable long id,
                                                               @RequestParam(value = "salary", defaultValue = "") float salary) {

        logger.info("Init modifyEmployeeByDepartment");

        Employee employee = employeeService.modifyBySalary(id, salary);

        logger.info("End modifyEmployeeByDepartment");

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }*/

    /*--------------------------------DELETE----------------------------------*/
    @Operation(summary = "Delete employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee deleted",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Employee doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping("/developers/employees/{id}")
    public ResponseEntity<Response> deleteEmployee(@PathVariable long id) {

        logger.info("Init deleteEmployee");

        employeeService.deleteEmployee(id);

        logger.info("End deleteEmployee");

        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    /*--------------------------------EXCEPTION----------------------------------*/
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(EmployeeNotFoundException enfe){
        Response response = Response.errorResponse(NOT_FOUND, enfe.getMessage());
        logger.error(enfe.getMessage(), enfe);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
