package com.bosonit.BD1crud.application.Asignatura;

import com.bosonit.BD1crud.domain.Asignatura;
import com.bosonit.BD1crud.infraestructure.controller.dto.input.AsignaturaInputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.output.AsignaturaOutputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.output.StudentOutputDtoSimple;

import java.util.List;

public interface AsignaturaService {
    AsignaturaOutputDto addAsignatura(AsignaturaInputDto asignaturaInputDto);
    void deleteAsignaturaPorId(int id);
    AsignaturaOutputDto modificarAsignaturaPorId(int id);
    List<Asignatura> listarAsignaturas();
    AsignaturaOutputDto buscarAsignaturaPorId(int id);
//    StudentOutputDtoSimple apuntarAsignatura(int idAsignatura, String idAlumno);
    List<AsignaturaOutputDto> buscarAsignaturaPorNombre(String nombre);

    Asignatura apuntarEstudianteAsignatura(int idAsignatura, String idEstudiante);
    Asignatura apuntarProfesorAsignatura(int idAsignatura, int idProfesor);

    List<AsignaturaOutputDto> apuntarEstudianteListaAsignaturas(List<Integer> listaIdAsignaturas, String idEstudiante);

//    List<AsignaturaOutputDto> asignaturasEstudiante(String idEstudiante);


}
