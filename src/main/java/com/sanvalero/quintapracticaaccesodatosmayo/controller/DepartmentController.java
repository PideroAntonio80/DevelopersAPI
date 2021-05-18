package com.sanvalero.quintapracticaaccesodatosmayo.controller;

import com.sanvalero.quintapracticaaccesodatosmayo.domain.Department;
import com.sanvalero.quintapracticaaccesodatosmayo.domain.Employee;
import com.sanvalero.quintapracticaaccesodatosmayo.domain.dto.DepartmentDTO;
import com.sanvalero.quintapracticaaccesodatosmayo.exception.DepartmentNotFoundException;
import com.sanvalero.quintapracticaaccesodatosmayo.service.DepartmentService;
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
@Tag(name = "departments", description = "Departments information")
public class DepartmentController {

    private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    /*--------------------------------FIND_ALL----------------------------------*/
    @Operation(summary = "Get all departments list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Departments list",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Department.class)))),
            @ApiResponse(responseCode = "404", description = "Departments list failed",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/developers/departments", produces = "application/json")
    public ResponseEntity<Set<Department>> getDepartments() {

        logger.info("Init getDepartments");

        Set<Department> departments = departmentService.findAll();

        logger.info("End getDepartments");

        return ResponseEntity.status(HttpStatus.OK).body(departments);
    }

    /*--------------------------------FIND_BY_ID----------------------------------*/
    @Operation(summary = "Get department by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Department exists",
                    content = @Content(schema = @Schema(implementation = Department.class))),
            @ApiResponse(responseCode = "404", description = "Department doesn't exists",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/developers/departments/{id}", produces = "application/json")
    public ResponseEntity<Department> getDepartmentById(@PathVariable long id) {

        logger.info("Init getDepartmentById");

        Department department = departmentService.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));

        logger.info("End getDepartmentById");

        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    /*--------------------------------FIND_EMPLOYEES_BY_SALARY----------------------------------*/
    @Operation(summary = "Find employees by salary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employees list",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Employee.class)))),
            @ApiResponse(responseCode = "404", description = "There are not employees with this characteristics",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/developers/departments/{id}/salary", produces = "application/json")
    public ResponseEntity<Set<Employee>> findEmployeeBySalary(@PathVariable long id,
                                                              @RequestParam(value = "salary", defaultValue = "") float salary) {

        logger.info("Init findEmployeeBySalary");

        Set<Employee> myEmployees = departmentService.findEmployeeBySalary(id, salary);

        logger.info("End findEmployeeBySalary");

        return new ResponseEntity<>(myEmployees, HttpStatus.OK);
    }

    /*--------------------------------ADD----------------------------------*/
    @Operation(summary = "Add new department")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Department added",
                    content = @Content(schema = @Schema(implementation = Department.class))),
            @ApiResponse(responseCode = "404", description = "Department couldn't be added",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value= "/developers/departments", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {

        logger.info("Init addDepartment");

        Department addedDepartment = departmentService.addDepartment(department);

        logger.info("End addDepartment");

        return new ResponseEntity<>(addedDepartment, HttpStatus.CREATED);
    }

    /*--------------------------------MODIFY----------------------------------*/
    @Operation(summary = "Modify department")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Department modified",
                    content = @Content(schema = @Schema(implementation = Department.class))),
            @ApiResponse(responseCode = "404", description = "Department doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/developers/departments/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Department> modifyDepartment(@PathVariable long id, @RequestBody DepartmentDTO departmentDTO) {

        logger.info("Init modifyDepartment");

        Department department = departmentService.modifyDepartment(id, departmentDTO);

        logger.info("End modifyDepartment");

        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    /*--------------------------------DELETE----------------------------------*/
    @Operation(summary = "Deletes department")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Department deleted",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Department doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/developers/departments/{id}")
    public ResponseEntity<Response> deleteDepartment(@PathVariable long id) {

        logger.info("Init deleteDepartment");

        departmentService.deleteDepartment(id);

        logger.info("End deleteDepartment");

        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    /*--------------------------------EXCEPTION----------------------------------*/
    @ExceptionHandler(DepartmentNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(DepartmentNotFoundException dnfe){
        Response response = Response.errorResponse(NOT_FOUND, dnfe.getMessage());
        logger.error(dnfe.getMessage(), dnfe);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
