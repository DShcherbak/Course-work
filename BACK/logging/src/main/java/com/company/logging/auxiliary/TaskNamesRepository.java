package com.company.logging.auxiliary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TaskNamesRepository extends JpaRepository<TaskNames, TaskNamesRepository> {

    @Transactional
    @Modifying
    @Query("DELETE FROM TaskNames tn WHERE tn.subjectId = ?1")
    void deleteAllBySubjectId(Long id);

    @Query("Select tn from TaskNames tn where tn.subjectId = ?1 order by tn.number")
    List<TaskNames> getBySubjectIdOrderedByNumber(Long id);
}
