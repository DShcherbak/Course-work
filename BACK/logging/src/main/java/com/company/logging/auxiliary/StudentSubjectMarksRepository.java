package com.company.logging.auxiliary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface StudentSubjectMarksRepository extends JpaRepository<StudentSubjectMarks, Long> {

    @Query("SELECT ssm FROM StudentSubjectMarks ssm WHERE ssm.subjectId = ?1")
    List<StudentSubjectMarks> selectBySubjectId(Long subjectId);

    @Transactional
    @Modifying
    @Query("DELETE FROM StudentSubjectMarks ssm where ssm.subjectId = ?1")
    void deleteAllBySubjectId(Long id);

    @Query("SELECT ssm.studentId FROM StudentSubjectMarks ssm where ssm.subjectId = ?1")
    List<Long> getStudentBySubjectId(Long subjectId);

    @Query("SELECT ssm FROM StudentSubjectMarks ssm WHERE ssm.subjectId = ?1")
    List<StudentSubjectMarks> getMarksBySubject(Long subjectId);
}
