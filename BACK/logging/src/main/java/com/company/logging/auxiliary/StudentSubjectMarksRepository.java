package com.company.logging.auxiliary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentSubjectMarksRepository extends JpaRepository<StudentSubjectMarks, Long> {
    @Query("SELECT ssm FROM StudentSubjectMarks ssm WHERE ssm.subjectId = ?1")
    List<StudentSubjectMarks> selectBySubjectId(Long subjectId);
}
