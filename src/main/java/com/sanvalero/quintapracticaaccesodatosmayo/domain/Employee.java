package com.sanvalero.quintapracticaaccesodatosmayo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 * Creado por @ author: Pedro Orós
 * el 18/05/2021
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "employee")
public class Employee {

    @Schema(description = "Employee identification number", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Employee complete name", example = "Johnny Lawrence", required = true)
    @NotBlank
    @Column
    private String name;

    @Schema(description = "Contract date", example = "08/07/2012")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column
    private LocalDate contractDate;

    @Schema(description = "Employee salary", example = "3000")
    @Column
    private float salary;

    @Schema(description = "Employee's department identity")
    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonBackReference
    private Department department;
}
