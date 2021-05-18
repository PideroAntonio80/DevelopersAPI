package com.sanvalero.quintapracticaaccesodatosmayo.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

/**
 * Creado por @ author: Pedro Orós
 * el 18/05/2021
 */

@Data
@NoArgsConstructor
public class DepartmentDTO {

    @Schema(description = "Department name", example = "Android", required = true)
    private String name;

    @Schema(description = "Does this department permanent contract?", example = "true")
    private boolean permanent;
}
