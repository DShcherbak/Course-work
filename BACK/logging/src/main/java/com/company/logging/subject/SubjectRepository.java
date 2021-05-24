package com.company.logging.subject;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Optional<Subject> findByName(String name);

    Optional<Subject> findByProfessorId(Long id);

    List<Subject> findAllByProfessorId(Long id);

}
