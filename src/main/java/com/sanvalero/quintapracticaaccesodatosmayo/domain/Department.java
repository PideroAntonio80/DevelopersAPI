package com.sanvalero.quintapracticaaccesodatosmayo.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Creado por @ author: Pedro Orós
 * el 18/05/2021
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "department")
public class Department {

    @Schema(description = "Department identification code", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Department name", example = "Android", required = true)
    @NotBlank
    @Column
    private String name;

    @Schema(description = "Does this department permanent contract?", example = "true")
    @Column
    private boolean permanent;

    @Schema(description = "Department's employees list")
    @OneToMany(mappedBy = "department", cascade = CascadeType.REMOVE)
    private List<Employee> employees;
}
