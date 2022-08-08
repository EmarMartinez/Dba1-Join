package com.bosonit.BD1crud.infraestructure.controller;

import com.bosonit.BD1crud.application.Asignatura.AsignaturaServiceImpl;
import com.bosonit.BD1crud.application.persona.PersonaServiceImpl;
import com.bosonit.BD1crud.application.profesor.ProfesorServiceImpl;
import com.bosonit.BD1crud.application.student.StudentServiceImpl;
import com.bosonit.BD1crud.domain.Asignatura;
import com.bosonit.BD1crud.exceptions.IdNoEncontrada;
import com.bosonit.BD1crud.exceptions.UnprocesableException;
import com.bosonit.BD1crud.infraestructure.controller.dto.input.PersonaInputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.input.ProfesorInputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.input.StudentInputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.output.AsignaturaOutputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.output.PersonaOutputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.output.ProfesorOutputDtoSimple;
import com.bosonit.BD1crud.infraestructure.controller.dto.output.StudentOutputDtoSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ControladorPut {
    @Autowired
    PersonaServiceImpl personaServiceImpl;

    @Autowired
    StudentServiceImpl studentServiceImpl;

    @Autowired
    ProfesorServiceImpl profesorServiceImpl;

    @Autowired
    AsignaturaServiceImpl asignaturaServiceImpl;

    @PutMapping("modificar/{id}")
    public PersonaOutputDto modificarPorId(@PathVariable String id, @RequestBody PersonaInputDto personaInputDto) {
        try {
            return personaServiceImpl.modificarPorId(id, personaInputDto);
        }
        catch(NoSuchElementException e) {
            throw new IdNoEncontrada("El id " + id + " no se ha encontrado");
        }
        catch(UnprocesableException e) {
            throw new UnprocesableException(e.getMessage());
        }
    }
    @PutMapping("estudiante/modificar/{id}")
    public StudentOutputDtoSimple modificarEstudiantePorId(@PathVariable String id, @RequestBody StudentInputDto studentInputDto) {
        try {
            return studentServiceImpl.modificarEstudiantePorId(id, studentInputDto);
        }
        catch(NoSuchElementException e) {
            throw new IdNoEncontrada("El id " + id + " no se ha encontrado");
        }
        catch(UnprocesableException e) {
            throw new UnprocesableException(e.getMessage());
        }
    }
    @PutMapping("profesor/modificar/{id}")
    public ProfesorOutputDtoSimple modificarProfesorPorId(@PathVariable int id, @RequestBody ProfesorInputDto profesorInputDto) {
        try {
            return profesorServiceImpl.modificarProfesorPorId(id, profesorInputDto);
        }
        catch(NoSuchElementException e) {
            throw new IdNoEncontrada("El id " + id + " no se ha encontrado");
        }
        catch(UnprocesableException e) {
            throw new UnprocesableException(e.getMessage());
        }
    }

    @PutMapping("asignatura/{idAsignatura}/estudiante/{idEstudiante}")
    Asignatura apuntarEstudianteAsignatura(@PathVariable int idAsignatura, @PathVariable String idEstudiante) {
       return asignaturaServiceImpl.apuntarEstudianteAsignatura(idAsignatura, idEstudiante);
    }
    @PutMapping("asignatura/{idAsignatura}/profesor/{idProfesor}")
    Asignatura apuntarEstudianteAsignatura(@PathVariable int idAsignatura, @PathVariable int idProfesor) {
        return asignaturaServiceImpl.apuntarProfesorAsignatura(idAsignatura, idProfesor);
    }
    @PutMapping("asignatura/estudiante/{idEstudiante}")
    List<AsignaturaOutputDto> apuntarEstudianteListaAsignaturas(@RequestBody List<Integer> listaIdAsignaturas, @PathVariable String idEstudiante) {
        return asignaturaServiceImpl.apuntarEstudianteListaAsignaturas(listaIdAsignaturas, idEstudiante);
    }
}
