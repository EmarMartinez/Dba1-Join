package com.bosonit.BD1crud.infraestructure.controller.dto.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonaInputDto {


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


}
