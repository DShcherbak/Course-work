package com.company.logging.auxiliary;

import com.company.logging.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface SubjectGroupRepository extends JpaRepository<SubjectGroup, Long> {

    @Query("SELECT sg.groupId FROM SubjectGroup sg WHERE sg.subjectId = ?1")
    List<Long> selectGroupsBySubjectId(Long subjectId);


    @Transactional
    @Modifying
    @Query("DELETE FROM SubjectGroup sg where sg.subjectId = ?1")
    void deleteAllBySubjectId(Long id);
}
