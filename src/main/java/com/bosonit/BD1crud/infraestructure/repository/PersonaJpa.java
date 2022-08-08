package com.bosonit.BD1crud.infraestructure.repository;

import com.bosonit.BD1crud.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaJpa extends JpaRepository<Persona, String> {
    @Query("SELECT p FROM Persona p WHERE p.name = ?1")
    List<Persona> buscarPersonasPorNombre(String nombre);
}
