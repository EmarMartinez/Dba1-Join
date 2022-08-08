package com.bosonit.BD1crud.application.persona;

import com.bosonit.BD1crud.domain.Persona;
import com.bosonit.BD1crud.infraestructure.controller.dto.input.PersonaInputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.output.PersonaOutputDto;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;

public interface PersonaService {
    PersonaOutputDto addPersona(PersonaInputDto personaInputDto);
    PersonaOutputDto buscarPorId(String id);
    ResponseEntity<List<PersonaOutputDto>> buscarPorNombre(String nombre);
    ResponseEntity<List<PersonaOutputDto>> buscarTodos();
    PersonaOutputDto modificarPorId(String id, PersonaInputDto personaInputDto);
    void borrarPorId(String id);


    List<Persona> getData(HashMap<String, Object> data);
}
