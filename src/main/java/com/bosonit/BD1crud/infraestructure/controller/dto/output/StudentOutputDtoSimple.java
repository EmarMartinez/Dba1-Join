package com.bosonit.BD1crud.infraestructure.controller.dto.output;

import com.bosonit.BD1crud.domain.Asignatura;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentOutputDtoSimple {
    String id_persona;
    String id;
    int num_hours_week;
    String comments;
    String id_profesor;
    String branch;
    Set<Asignatura> asignaturas;
}
