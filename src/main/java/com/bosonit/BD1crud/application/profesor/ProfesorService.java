package com.bosonit.BD1crud.application.profesor;

import com.bosonit.BD1crud.domain.Profesor;
import com.bosonit.BD1crud.infraestructure.controller.dto.input.ProfesorInputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.input.StudentInputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.output.ProfesorOutputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.output.ProfesorOutputDtoSimple;
import com.bosonit.BD1crud.infraestructure.controller.dto.output.StudentOutputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.output.StudentOutputDtoSimple;

import java.util.List;

public interface ProfesorService {
    ProfesorOutputDtoSimple addProfesor(ProfesorInputDto profesorInputDto);

    List<ProfesorOutputDtoSimple> profesorList();
    ProfesorOutputDtoSimple getProfesor(int id);
    ProfesorOutputDto getProfesorFull(int id);
    ProfesorOutputDtoSimple modificarProfesorPorId(int id, ProfesorInputDto profesorInputDto);
    void borrarProfesorePorId(int id);


}
