package com.company.logging.auxiliary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskNamesRepository extends JpaRepository<TaskNames, TaskNamesRepository> {

    void deleteAllBySubjectId(Long id);

    @Query("Select tn from TaskNames tn where tn.subjectId = ?1 order by tn.number")
    List<TaskNames> getBySubjectIdOrderedByNumber(Long id);
}
