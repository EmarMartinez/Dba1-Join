package com.bosonit.BD1crud.infraestructure.controller.dto.input;

import com.bosonit.BD1crud.domain.Asignatura;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInputDto {
    String id;
    int num_hours_week;
    String comments;
    String id_profesor;
    String branch;
    List<Asignatura> asignaturas;
}
