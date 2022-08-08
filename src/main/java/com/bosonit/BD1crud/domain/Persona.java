package com.bosonit.BD1crud.domain;

import com.bosonit.BD1crud.autoincrementales.PersonaSequenceIdGenerator;
import com.bosonit.BD1crud.exceptions.UnprocesableException;
import com.bosonit.BD1crud.infraestructure.controller.dto.input.PersonaInputDto;
import com.bosonit.BD1crud.infraestructure.controller.dto.output.PersonaOutputDto;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ausencias_seq")
    @GenericGenerator(

            name = "ausencias_seq",

            strategy = "com.bosonit.BD1crud.autoincrementales.PersonaSequenceIdGenerator",

            parameters = {

                    @Parameter(name = PersonaSequenceIdGenerator.INCREMENT_PARAM, value = "1"),

                    @Parameter(name = PersonaSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = ""),

                    @Parameter(name = PersonaSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%08d")

            })

    @Column(name = "id")
    private String id;
    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;

    @OneToOne(mappedBy = "persona", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Student student;

    public void setStudent(Student studentTest) {
        if(this.profesor == null) {
            if (studentTest == null) {
                if (this.student != null) {
                    this.student.setPersona(null);
                }
            } else {
                studentTest.setPersona(this);
            }
            this.student = studentTest;
        }
        else {
            throw new UnprocesableException("No puede haber un Estudiante que a la vez sea profesor");
        }
    }
    @OneToOne(mappedBy = "persona", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Profesor profesor;
    public void setProfesor(Profesor profesorTest) {
        if (this.student == null) {
            if (profesorTest == null) {
                if (this.profesor != null) {
                    this.profesor.setPersona(null);
                }
            } else {
                profesorTest.setPersona(this);
            }
            this.profesor = profesorTest;
        }
        else {
            throw new UnprocesableException("No puede haber un profesor que sea a la vez un alumno");
        }
    }


    public void Validador() throws Exception{

        if(usuario == null || usuario.length()<6 || usuario.length() > 10 )
        {

            throw new UnprocesableException("El usuario no puede ser null, y tiene que tener entre 6 y 10 caracteres");


        }
        if(password == null )
        {
            throw new UnprocesableException("El password no puede ser null");
        }
        if(name == null )
        {
            throw new UnprocesableException("El nombre no puede ser null");
        }
        if(company_email == null )
        {
            throw new UnprocesableException("El email no puede ser null");
        }
        if(personal_email == null )
        {
            throw new UnprocesableException("El email no puede ser null");
        }
        if(city == null )
        {
            throw new UnprocesableException("La ciudad no puede ser null");
        }
        if(created_date == null )
        {
            throw new UnprocesableException("La fecha no puede ser null");
        }


    }
    public Persona DtoToPersona(PersonaInputDto personaInputDto) throws Exception{
        try {
            Persona persona = new Persona();
            persona.setUsuario(personaInputDto.getUsuario());
            persona.setPassword(personaInputDto.getPassword());
            persona.setName(personaInputDto.getName());
            persona.setSurname(personaInputDto.getSurname());
            persona.setCompany_email(personaInputDto.getCompany_email());
            persona.setPersonal_email(personaInputDto.getPersonal_email());
            persona.setCity(personaInputDto.getCity());
            persona.setActive(personaInputDto.isActive());
            persona.setCreated_date(personaInputDto.getCreated_date());
            persona.setImagen_url(personaInputDto.getImagen_url());
            persona.setTermination_date(personaInputDto.getTermination_date());
            persona.Validador();
            return persona;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw new UnprocesableException(e.getMessage());
        }
    }
    public PersonaOutputDto PersonaToDto(Persona persona) {

        PersonaOutputDto personaOutputDto = new PersonaOutputDto();
        personaOutputDto.setUsuario(persona.getUsuario());
        personaOutputDto.setPassword(persona.getPassword());
        personaOutputDto.setName(persona.getName());
        personaOutputDto.setSurname(persona.getSurname());
        personaOutputDto.setCompany_email(persona.getCompany_email());
        personaOutputDto.setPersonal_email(persona.getPersonal_email());
        personaOutputDto.setCity(persona.getCity());
        personaOutputDto.setActive(persona.isActive());
        personaOutputDto.setCreated_date(persona.getCreated_date());
        personaOutputDto.setImagen_url(persona.getImagen_url());
        personaOutputDto.setTermination_date(persona.getTermination_date());
        return personaOutputDto;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id='" + id + '\'' +
                ", usuario='" + usuario + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", company_email='" + company_email + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
