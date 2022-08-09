package com.bosonit.BD1crud.application.persona;

import com.bosonit.BD1crud.domain.Persona;
import com.bosonit.BD1crud.domain.Persona_;
import com.bosonit.BD1crud.domain.Student;
import com.bosonit.BD1crud.domain.Student_;
import com.bosonit.BD1crud.exceptions.UnprocesableException;
import com.bosonit.BD1crud.infraestructure.controller.dto.input.PersonaInputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.output.PersonaOutputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.output.PersonaOutputDtoJoin;
import com.bosonit.BD1crud.infraestructure.repository.PersonaJpa;
import com.bosonit.BD1crud.infraestructure.repository.StudentJpa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.bosonit.BD1crud.infraestructure.controller.ControladorGet.*;

@Service
@AllArgsConstructor
public class PersonaServiceImpl implements PersonaService{

    @Autowired
    PersonaJpa personaJpa;



    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public PersonaOutputDto addPersona(PersonaInputDto personaInputDto){

            Persona persona = new Persona();
            try {
                personaJpa.save(persona.DtoToPersona(personaInputDto));
                return persona.PersonaToDto(persona.DtoToPersona(personaInputDto));
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
                throw new UnprocesableException(e.getMessage());
            }


    }

    @Override
    public PersonaOutputDto buscarPorId(String id) {

            Persona persona = new Persona();
            return persona.PersonaToDto(personaJpa.findById(id).orElseThrow());

    }

    @Override
    public ResponseEntity<List<PersonaOutputDto>> buscarPorNombre(String nombre) {

        return new ResponseEntity<>( personaJpa.buscarPersonasPorNombre(nombre).stream().map(n->n.PersonaToDto(n)).toList(),HttpStatus.OK);


    }

    @Override
    public ResponseEntity<List<PersonaOutputDto>> buscarTodos() {

        return new ResponseEntity<>(personaJpa.findAll().stream().map(n->n.PersonaToDto(n)).toList(),HttpStatus.OK);
    }

    @Override
    public PersonaOutputDto modificarPorId(String id, PersonaInputDto personaInputDto) {

            personaJpa.findById(id).orElseThrow();
            try {
            Persona persona = new Persona();
            Persona personaMod = persona.DtoToPersona(personaInputDto);
            personaMod.setId(id);
            personaJpa.save(personaMod);
            return persona.PersonaToDto(personaMod);
        }
            catch (Exception e) {
                System.out.println(e.getMessage());
                throw new UnprocesableException(e.getMessage());
            }


    }

    @Override
    public void borrarPorId(String id) {

            personaJpa.delete(personaJpa.findById(id).orElseThrow());
    }

    @Override
    public List<Persona> getData(HashMap<String, Object> data) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Persona> query= cb.createQuery(Persona.class);
        Root<Persona> root = query.from(Persona.class);

        List<Predicate> predicates = new ArrayList<>();
        data.forEach((field,value) ->
        {
            switch (field)
            {

                case "usuario":
                    predicates.add(cb.like(root.get(field), (String)value));
                    break;
                case "name":
                    predicates.add(cb.like(root.get(field),"%"+(String)value+"%"));
                    break;
                case "surname":
                    predicates.add(cb.like(root.get(field),"%"+(String)value+"%"));
                    break;
                case "creacion":
                    String dateCondition=(String) data.get("dateCondition");
                    switch (dateCondition) {
                        case GREATER_THAN -> predicates.add(cb.greaterThan(root.<Date>get(field), (Date) value));
                        case LESS_THAN -> predicates.add(cb.lessThan(root.<Date>get(field), (Date) value));
                        case EQUAL -> predicates.add(cb.equal(root.<Date>get(field), (Date) value));
                    }
                    break;
                case "ordenacion":
                    String ordenacion = (String) data.get("ordenacion");
                    switch (ordenacion) {
                        case PorNombre:
                            query.orderBy(cb.asc(root.get(Persona_.name)));
                            break;
                        case PorUsername:
                            query.orderBy(cb.asc(root.get(Persona_.usuario)));
                            break;
                    }
            }

        });


        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<Persona> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult((Integer.parseInt((String) data.get("pagina"))-1)*Integer.parseInt((String) data.get("registros")));
        typedQuery.setMaxResults(Integer.parseInt((String)data.get("registros")));
        return typedQuery.getResultList();
    }

    @Override
    @JsonIgnore
    public List<PersonaOutputDtoJoin> getJoinData(String idusuario) {
//        String id_studenttest = personaJpa.findById(idusuario).orElseThrow().getStudent().getId_student();
//
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Persona> cq = cb.createQuery(Persona.class);
//        Root<Persona> root = cq.from(Persona.class);
//        Join<Persona, Student> personaStudent = root.join(Persona_.STUDENT);
//
//        ParameterExpression<String> id_student = cb.parameter(String.class);
//        cq.where(cb.like(personaStudent.get(Student_.id_student), id_student));
//
//        TypedQuery<Persona> q = entityManager.createQuery(cq);
//        q.setParameter(id_student, id_studenttest);
//        List<Persona> personaJoin = q.getResultList();
//
//        return personaJoin;






        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PersonaOutputDtoJoin> query = cb.createQuery(PersonaOutputDtoJoin.class);

        String id_student = personaJpa.findById(idusuario).orElseThrow().getStudent().getId_student();

        Root<Persona> personaTable = query.from(Persona.class);
        Join<Persona, Student> studentJoin = personaTable.join(Persona_.STUDENT, JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(personaTable.get(Persona_.ID), idusuario));
        predicates.add(cb.equal(studentJoin.get(Student_.id_student), id_student));

        query.multiselect(
                personaTable.get(Persona_.name),
                personaTable.get(Persona_.surname),
                studentJoin.get(Student_.branch),
                studentJoin.get(Student_.COMMENTS));

        query.where(predicates.stream().toArray(Predicate[]::new));
        TypedQuery<PersonaOutputDtoJoin> typedQuery = entityManager.createQuery(query);

        List<PersonaOutputDtoJoin> resultList = typedQuery.getResultList();
        return resultList;

    }


}
