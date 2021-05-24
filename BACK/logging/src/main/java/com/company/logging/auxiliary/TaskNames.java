package com.company.logging.auxiliary;

import javax.persistence.*;

@Entity
@Table
public class TaskNames {
    @Id
    @SequenceGenerator(
            name = "tasks_sequence",
            sequenceName = "tasks_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tasks_sequence"
    )
    Long id;
    Long subjectId;
    Long number;
    String name;

    public TaskNames() {
    }

    public TaskNames(Long id, Long subjectId, String name, Long number) {
        this.id = id;
        this.subjectId = subjectId;
        this.name = name;
        this.number = number;
    }

    public TaskNames(Long subjectId, String name, Long number) {
        this.subjectId = subjectId;
        this.name = name;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
