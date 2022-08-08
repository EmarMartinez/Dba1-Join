package com.bosonit.BD1crud.domain;

import com.bosonit.BD1crud.infraestructure.controller.dto.input.AsignaturaInputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.output.AsignaturaOutputDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Asignatura {
    @Id
    @GeneratedValue
    int id_asignatura;

    @JsonIgnore
    @ManyToMany
    @JoinTable(

            joinColumns = @JoinColumn,
            inverseJoinColumns = @JoinColumn
    )
    Set<Student> estudiantesApuntados = new HashSet<>();

    @JsonIgnore
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    Profesor profesor;
    String asignatura;
    String comments;
    Date initial_date;
    Date finish_date;
    public Set<Student> getEstudiantesApuntados() {
        return estudiantesApuntados;
    }
    public void AsignaturaInputDtoToAsignatura(AsignaturaInputDto asignaturaInputDto) {
        this.asignatura = asignaturaInputDto.getAsignatura();
        this.comments = asignaturaInputDto.getComments();
        this.initial_date = asignaturaInputDto.getInitial_date();
        this.finish_date = asignaturaInputDto.getFinish_date();
    }
    public AsignaturaOutputDto AsignaturaToAsignaturaOutputDto(Asignatura asignatura) {

        AsignaturaOutputDto asignaturaOutputDto = new AsignaturaOutputDto();
        asignaturaOutputDto.setId_asignatura(asignatura.getId_asignatura());
        asignaturaOutputDto.setComments(asignatura.getComments());
        asignaturaOutputDto.setInitial_date(asignatura.getInitial_date());
        asignaturaOutputDto.setFinish_date(asignatura.getFinish_date());
//        asignaturaOutputDto.setEstudiantesApuntados(asignatura.getEstudiantesApuntados());
        return asignaturaOutputDto;
    }

    public void apuntarEstudiante(Student student) {
        estudiantesApuntados.add(student);
    }

    public void apuntarProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}
