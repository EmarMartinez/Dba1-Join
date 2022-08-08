package com.bosonit.BD1crud.application.profesor;

import com.bosonit.BD1crud.domain.Persona;
import com.bosonit.BD1crud.domain.Profesor;
import com.bosonit.BD1crud.domain.Student;
import com.bosonit.BD1crud.infraestructure.controller.dto.input.ProfesorInputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.output.ProfesorOutputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.output.ProfesorOutputDtoSimple;
import com.bosonit.BD1crud.infraestructure.controller.dto.output.StudentOutputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.output.StudentOutputDtoSimple;
import com.bosonit.BD1crud.infraestructure.repository.PersonaJpa;
import com.bosonit.BD1crud.infraestructure.repository.ProfesorJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorServiceImpl implements ProfesorService{

    @Autowired
    PersonaJpa personaJpa;

    @Autowired
    ProfesorJpa profesorJpa;

    @Override
    public ProfesorOutputDtoSimple addProfesor(ProfesorInputDto profesorInputDto) {
        Profesor profesor = new Profesor();
        profesor.ProfesorInputDtoToProfesor(profesorInputDto);
        Persona persona = personaJpa.findById(profesorInputDto.getId_persona()).orElseThrow();
        profesor.setPersona(persona);
        persona.setProfesor(profesor);
        profesorJpa.save(profesor);
        return profesor.ProfesorToProfesorOutputDtoSimple(profesor);
    }

    @Override
    public List<ProfesorOutputDtoSimple> profesorList() {

            List<Profesor> listaCompleta;
            listaCompleta = profesorJpa.findAll().stream().toList();
            return  listaCompleta.stream().map(n->n.ProfesorToProfesorOutputDtoSimple(n)).toList();


    }

    @Override
    public ProfesorOutputDtoSimple getProfesor(int id) {

        Profesor profesor = profesorJpa.findById(id).orElseThrow();
        return profesor.ProfesorToProfesorOutputDtoSimple(profesor);
    }

    @Override
    public ProfesorOutputDto getProfesorFull(int id) {

        Profesor profesor = profesorJpa.findById(id).orElseThrow();
        ProfesorOutputDto profesorOutputDto;
        profesorOutputDto = profesor.ProfesorToProfesorOutputDto(profesor);
        return profesorOutputDto;
    }

    @Override
    public ProfesorOutputDtoSimple modificarProfesorPorId(int id, ProfesorInputDto profesorInputDto) {
        Profesor profesorMod = profesorJpa.findById(id).orElseThrow();
        profesorMod.ProfesorInputDtoToProfesor(profesorInputDto);
        profesorJpa.save(profesorMod);
        return profesorMod.ProfesorToProfesorOutputDtoSimple(profesorMod);
    }

    @Override
    public void borrarProfesorePorId(int id) {

        profesorJpa.delete(profesorJpa.findById(id).orElseThrow());
    }

}
