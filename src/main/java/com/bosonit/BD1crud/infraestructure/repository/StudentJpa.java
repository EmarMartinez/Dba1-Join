package com.bosonit.BD1crud.infraestructure.repository;

import com.bosonit.BD1crud.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentJpa extends JpaRepository<Student, String> {
}
