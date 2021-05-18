package com.sanvalero.quintapracticaaccesodatosmayo.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 * Creado por @ author: Pedro Orós
 * el 18/05/2021
 */

@Data
@NoArgsConstructor
public class EmployeeDTO {

    @Schema(description = "Employee complete name", example = "Johnny Lawrence", required = true)
    private String name;

    @Schema(description = "Contract date", example = "08/07/2012")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate contractDate;

    @Schema(description = "Employee salary", example = "3000")
    private float salary;
}
