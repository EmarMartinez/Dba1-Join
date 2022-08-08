package com.bosonit.BD1crud.infraestructure.controller.dto.output;

import com.bosonit.BD1crud.domain.Asignatura;
import com.bosonit.BD1crud.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AsignaturaOutputDto {
    int id_asignatura;
    String asignatura;
    String comments;
    Date initial_date;
    Date finish_date;

}
