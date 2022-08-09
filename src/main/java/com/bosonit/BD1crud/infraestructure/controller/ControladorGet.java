package com.bosonit.BD1crud.infraestructure.controller;

import com.bosonit.BD1crud.application.Asignatura.AsignaturaServiceImpl;
import com.bosonit.BD1crud.application.persona.PersonaServiceImpl;
import com.bosonit.BD1crud.application.profesor.ProfesorServiceImpl;
import com.bosonit.BD1crud.application.student.StudentServiceImpl;
import com.bosonit.BD1crud.domain.Persona;
import com.bosonit.BD1crud.exceptions.IdNoEncontrada;
import com.bosonit.BD1crud.exceptions.UnprocesableException;
import com.bosonit.BD1crud.infraestructure.controller.dto.input.StudentInputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.output.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.*;

@RestController
public class ControladorGet {

    @Autowired
    PersonaServiceImpl personaServiceImpl;

    @Autowired
    StudentServiceImpl studentServiceImpl;

    @Autowired
    ProfesorServiceImpl profesorServiceImpl;

    @Autowired
    AsignaturaServiceImpl asignaturaServiceImpl;

    @Autowired
    EntityManager em;

    public static final String GREATER_THAN="greater";
    public static final String LESS_THAN="less";
    public static final String EQUAL="equal";

    public static final String PorNombre="nombre";
    public static final String PorUsername="username";

    @GetMapping("id/{id}")
    public PersonaOutputDto buscarPorId(@PathVariable String id) {
        try {
            personaServiceImpl.buscarPorId(String.valueOf(id));
        }
        catch(NoSuchElementException e) {

            throw new IdNoEncontrada("El id " + id + " no se ha encontrado");
        }
        return personaServiceImpl.buscarPorId(String.valueOf(id));

    }


    @GetMapping("persona/{nombre}")
    public ResponseEntity<List<PersonaOutputDto>> buscarPorNombre(@PathVariable String nombre) {
        return personaServiceImpl.buscarPorNombre(nombre);
    }


    @GetMapping("entradas")
    public ResponseEntity<List<PersonaOutputDto>> listaCompleta() {

        return personaServiceImpl.buscarTodos();
    }


    @GetMapping("estudiante/{id}")
    public Object getStudent(@PathVariable String id, @RequestParam(required = false, defaultValue = "simple") String outputType) {
    try {
        if (outputType.equals("full")) {
            return studentServiceImpl.getStudentFull(id);
        }
        else {
            return studentServiceImpl.getStudent(id);
        }
    }
    catch(Exception e) {

        throw new IdNoEncontrada("No se encontro la ID");

        }
    }

    @GetMapping("estudiante/entradas")
    public List<StudentOutputDtoSimple> mostrarEstudiantes() {

        return studentServiceImpl.mostrarEstudiantes();
    }

    @GetMapping("profesor/entradas")
    public List<ProfesorOutputDtoSimple> mostrarProfesores() {

        return profesorServiceImpl.profesorList();
    }

    @GetMapping("profesor/{id}")
    public Object getProfesor(@PathVariable int id, @RequestParam(required = false, defaultValue = "simple") String outputType) {
        try {
            if (outputType.equals("full")) {
                return profesorServiceImpl.getProfesorFull(id);
            } else {
                return profesorServiceImpl.getProfesor(id);
            }
        }
        catch(Exception e) {

            throw new IdNoEncontrada("No se encontro la ID");

        }
    }
    @GetMapping("asignatura/nombre/{nombre}")
    public List<AsignaturaOutputDto> buscarAsignaturaPorNombre(@PathVariable String nombre) {
        return asignaturaServiceImpl.buscarAsignaturaPorNombre(nombre);
    }
    @JsonIgnore
    @GetMapping("asignatura/estudiante/{idEstudiante}")
    public List<AsignaturaOutputDto> buscarAsignaturasEstudiante(@PathVariable String idEstudiante) {
        return studentServiceImpl.asignaturasEstudiante(idEstudiante);
    }

    @GetMapping("persona/customquery")
    public List<Persona> getPersona(@RequestParam(required=false, name="usuario") String usuario,
                                    @RequestParam(required=false, name="name") String name,
                                    @RequestParam(required=false, name="surname") String surname,
                                    @RequestParam(required=false, name="creacion") @DateTimeFormat(pattern="dd-MM-yyyy") Date creacion,
                                    @RequestParam(required=false, name="datecondicion") String dateCondition,
                                    @RequestParam(required=false, name="ordenacion") String ordenacion,
                                    @RequestParam(required=false, name="registros", defaultValue = "10") String registros,
                                    @RequestParam(name="pagina") String pagina) {
        HashMap<String, Object> data = new HashMap<>();
        if (usuario!=null)
            data.put("usuario",usuario);
        if (name!=null)
            data.put("name",name);
        if (surname!=null)
            data.put("surname",surname);
        if (dateCondition==null)
            dateCondition=GREATER_THAN;
        if (!dateCondition.equals(GREATER_THAN) && 	!dateCondition.equals(LESS_THAN) && !dateCondition.equals(EQUAL))
            dateCondition=GREATER_THAN;
        if (creacion!=null)
        {
            data.put("creacion",creacion);
            data.put("dateCondition",dateCondition);
        }
        if (ordenacion!=null) {
            if (ordenacion.equals(PorUsername)) {
                data.put("ordenacion", ordenacion);
            } else {
                ordenacion = PorNombre;
                data.put("ordenacion", ordenacion);
            }
        }
        if (registros!=null) {
            data.put("registros",registros);
        }
        data.put("pagina",pagina);
        return personaServiceImpl.getData(data);
    }
    @GetMapping("persona/customqueryjoin/{idusuario}")
    public List<PersonaOutputDtoJoin> getPersonaJoinStudent(@PathVariable String idusuario) {

        return personaServiceImpl.getJoinData(idusuario);
    }


}
