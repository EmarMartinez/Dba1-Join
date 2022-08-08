package com.bosonit.BD1crud.infraestructure.controller;

import com.bosonit.BD1crud.application.Asignatura.AsignaturaServiceImpl;
import com.bosonit.BD1crud.application.persona.PersonaService;
import com.bosonit.BD1crud.application.profesor.ProfesorServiceImpl;
import com.bosonit.BD1crud.application.student.StudentServiceImpl;
import com.bosonit.BD1crud.exceptions.UnprocesableException;
import com.bosonit.BD1crud.infraestructure.controller.dto.input.AsignaturaInputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.input.PersonaInputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.input.ProfesorInputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.input.StudentInputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.output.AsignaturaOutputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.output.PersonaOutputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.output.ProfesorOutputDtoSimple;
import com.bosonit.BD1crud.infraestructure.controller.dto.output.StudentOutputDtoSimple;
import com.bosonit.BD1crud.infraestructure.repository.ProfesorJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ControladorPost {

    @Autowired
    PersonaService personaservice;

    @Autowired
    StudentServiceImpl studentServiceImpl;

    @Autowired
    ProfesorServiceImpl profesorServiceImpl;

    @Autowired
    AsignaturaServiceImpl asignaturaServiceImpl;

    @PostMapping("/persona")
    public PersonaOutputDto crearPersona(@RequestBody PersonaInputDto personaInputDto){
        try {
            return personaservice.addPersona(personaInputDto);
        }
        catch(UnprocesableException e) {
            throw new UnprocesableException(e.getMessage());
        }
    }

    @PostMapping("estudiante")
    public StudentOutputDtoSimple addStudent(@RequestBody StudentInputDto studentInputDto) {
        return studentServiceImpl.addStudent(studentInputDto);
    }

    @PostMapping("profesor")
    public ProfesorOutputDtoSimple addProfesor(@RequestBody ProfesorInputDto profesorInputDto) {
        return profesorServiceImpl.addProfesor(profesorInputDto);
    }

    @PostMapping("asignatura")
    public AsignaturaOutputDto addAsignatura(@RequestBody AsignaturaInputDto asignaturaInputDto) {
        return asignaturaServiceImpl.addAsignatura(asignaturaInputDto);
    }

}
