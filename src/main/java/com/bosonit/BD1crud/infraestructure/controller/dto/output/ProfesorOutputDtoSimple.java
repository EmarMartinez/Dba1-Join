package com.bosonit.BD1crud.infraestructure.controller.dto.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorOutputDtoSimple {

    String id_persona;
    int id_profesor;
    String comments;
    String branch;
}
