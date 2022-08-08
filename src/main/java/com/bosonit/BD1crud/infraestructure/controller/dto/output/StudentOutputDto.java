package com.bosonit.BD1crud.infraestructure.controller.dto.output;

import com.bosonit.BD1crud.domain.Asignatura;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentOutputDto {
    String id_persona;
    String id;
    int num_hours_week;
    String comments;
    String id_profesor;
    String branch;
    Set<Asignatura> asignaturas;

    String usuario;
    String password;
    String name;
    String surname;
    String company_email;
    String personal_email;
    String city;
    boolean active;
    Date created_date;
    String imagen_url;
    Date termination_date;
}
