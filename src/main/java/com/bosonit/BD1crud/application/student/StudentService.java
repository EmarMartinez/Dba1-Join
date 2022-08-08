package com.bosonit.BD1crud.application.student;

import com.bosonit.BD1crud.infraestructure.controller.dto.input.StudentInputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.output.AsignaturaOutputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.output.StudentOutputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.output.StudentOutputDtoSimple;

import java.util.List;

public interface StudentService {

    StudentOutputDtoSimple addStudent(StudentInputDto studentInputDto);
    StudentOutputDtoSimple getStudent(String id);
    StudentOutputDto getStudentFull(String id);
    StudentOutputDtoSimple modificarEstudiantePorId(String id, StudentInputDto studentInputDto);
    void borrarEstudiantePorId(String id);
    List<StudentOutputDtoSimple> mostrarEstudiantes();


    List<AsignaturaOutputDto> asignaturasEstudiante(String idEstudiante);
}
