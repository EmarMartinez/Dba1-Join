package com.bosonit.BD1crud.infraestructure.repository;

import com.bosonit.BD1crud.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorJpa extends JpaRepository<Profesor, Integer> {
}
