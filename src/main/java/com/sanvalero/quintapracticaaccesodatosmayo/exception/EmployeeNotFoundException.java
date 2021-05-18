package com.sanvalero.quintapracticaaccesodatosmayo.exception;

/**
 * Creado por @ author: Pedro Orós
 * el 18/05/2021
 */
public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException() {
        super();
    }

    public EmployeeNotFoundException(String message){
        super(message);
    }

    public EmployeeNotFoundException(long id){
        super("Employee not found: " + id);
    }
}
