package com.bosonit.BD1crud.infraestructure.controller.dto.input;

import com.bosonit.BD1crud.domain.Persona;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorInputDto {

    int id_profesor;
    String id_persona;
    String comments;
    String branch;
}
