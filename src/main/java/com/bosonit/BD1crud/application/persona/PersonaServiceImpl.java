package com.bosonit.BD1crud.application.persona;

import com.bosonit.BD1crud.domain.Persona;
import com.bosonit.BD1crud.domain.Persona_;
import com.bosonit.BD1crud.exceptions.IdNoEncontrada;
import com.bosonit.BD1crud.exceptions.UnprocesableException;
import com.bosonit.BD1crud.infraestructure.controller.dto.input.PersonaInputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.output.PersonaOutputDto;
import com.bosonit.BD1crud.infraestructure.repository.PersonaJpa;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
                    switch (dateCondition)
                    {
                        case GREATER_THAN:
                            predicates.add(cb.greaterThan(root.<Date>get(field),(Date)value));
                            break;
                        case LESS_THAN:
                            predicates.add(cb.lessThan(root.<Date>get(field),(Date)value));
                            break;
                        case EQUAL:
                            predicates.add(cb.equal(root.<Date>get(field),(Date)value));
                            break;
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


}
