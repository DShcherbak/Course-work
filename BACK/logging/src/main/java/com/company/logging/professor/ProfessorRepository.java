package com.company.logging.professor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findByName(String name);

    @Query("SELECT p FROM Professor p where p.role = 'UNKNOWN'")
    List<Professor> getAllProfessorRequests();
}
