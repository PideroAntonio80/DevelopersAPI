package com.sanvalero.quintapracticaaccesodatosmayo.exception;

/**
 * Creado por @ author: Pedro Orós
 * el 18/05/2021
 */
public class DepartmentNotFoundException extends RuntimeException {

    public DepartmentNotFoundException() {
        super();
    }

    public DepartmentNotFoundException(String message){
        super(message);
    }

    public DepartmentNotFoundException(long id){
        super("Department not found: " + id);
    }
}
