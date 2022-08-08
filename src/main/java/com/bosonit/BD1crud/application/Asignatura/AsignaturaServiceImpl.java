package com.bosonit.BD1crud.application.Asignatura;

import com.bosonit.BD1crud.domain.Asignatura;
import com.bosonit.BD1crud.domain.Profesor;
import com.bosonit.BD1crud.domain.Student;
import com.bosonit.BD1crud.infraestructure.controller.dto.input.AsignaturaInputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.output.AsignaturaOutputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.output.StudentOutputDtoSimple;
import com.bosonit.BD1crud.infraestructure.repository.AsignaturaJpa;
import com.bosonit.BD1crud.infraestructure.repository.ProfesorJpa;
import com.bosonit.BD1crud.infraestructure.repository.StudentJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignaturaServiceImpl implements AsignaturaService{

    @Autowired
    AsignaturaJpa asignaturaJpa;

    @Autowired
    StudentJpa studentJpa;

    @Autowired
    ProfesorJpa profesorJpa;

    @Override
    public AsignaturaOutputDto addAsignatura(AsignaturaInputDto asignaturaInputDto) {
        Asignatura asignatura = new Asignatura();
        asignatura.AsignaturaInputDtoToAsignatura(asignaturaInputDto);
        asignaturaJpa.save(asignatura);
        return asignatura.AsignaturaToAsignaturaOutputDto(asignatura);

    }

    @Override
    public void deleteAsignaturaPorId(int id) {

    }

    @Override
    public AsignaturaOutputDto modificarAsignaturaPorId(int id) {
        return null;
    }

    @Override
    public List<Asignatura> listarAsignaturas() {
        return null;
    }

    @Override
    public AsignaturaOutputDto buscarAsignaturaPorId(int id) {
        return null;
    }

    @Override
    public List<AsignaturaOutputDto> buscarAsignaturaPorNombre(String nombre) {
        return asignaturaJpa.buscarAsignaturaPorNombre(nombre).stream().map(n->n.AsignaturaToAsignaturaOutputDto(n)).toList();
    }

    public Asignatura apuntarEstudianteAsignatura(int idAsignatura, String idEstudiante) {
        Asignatura asignatura = asignaturaJpa.findById(idAsignatura).orElseThrow();
        Student student = studentJpa.findById(idEstudiante).orElseThrow();
        asignatura.apuntarEstudiante(student);
        return asignaturaJpa.save(asignatura);
    }

    public Asignatura apuntarProfesorAsignatura(int idAsignatura, int idProfesor) {
        Asignatura asignatura = asignaturaJpa.findById(idAsignatura).orElseThrow();
        Profesor profesor = profesorJpa.findById(idProfesor).orElseThrow();
        asignatura.apuntarProfesor(profesor);
        return asignaturaJpa.save(asignatura);
    }

    @Override
    public List<AsignaturaOutputDto> apuntarEstudianteListaAsignaturas(List<Integer> listaIdAsignaturas, String idEstudiante) {
        List<Asignatura> lista = listaIdAsignaturas.stream().map(n-> asignaturaJpa.findById(n).orElseThrow()).toList();
        Student student = studentJpa.findById(idEstudiante).orElseThrow();
        lista.stream().forEach(n-> {
            n.apuntarEstudiante(student);
            asignaturaJpa.save(n);
        });


        return lista.stream().map(n->n.AsignaturaToAsignaturaOutputDto(n)).toList();
    }


}
