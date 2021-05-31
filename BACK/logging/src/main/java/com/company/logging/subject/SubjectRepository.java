package com.company.logging.subject;

import com.company.logging.auxiliary.StudentSubjectMarks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Optional<Subject> findByName(String name);

    List<Subject> findAllByProfessorId(Long id);

    @Query("SELECT s FROM Subject s inner join SubjectGroup sg on s.id = sg.subjectId WHERE sg.groupId = ?1")
    List<Subject> findAllByGroupId(Long groupId);

}
