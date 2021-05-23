package com.company.logging.subject;

import com.company.logging.marks.Marks;
import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;

@Entity
@Table
public class Subject {
    @Id
    @SequenceGenerator(
            name = "subject_sequence",
            sequenceName = "subject_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "subject_sequence"
    )
    private Long id;
    private String name;
    private Long professorId;
    @Transient
    private Marks marks;

    public Subject(String name, Long professorId) {
        this.name = name;
        this.professorId = professorId;
    }

    public Subject(String name, Long professorId, Marks marks) {
        this.name = name;
        this.professorId = professorId;
        this.marks = marks;
    }

    public Subject(Long id, String name, Long professorId, Marks marks) {
        this.id = id;
        this.name = name;
        this.professorId = professorId;
        this.marks = marks;
    }

    public Subject() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    @Transient
    @JsonGetter(value = "marks")
    public Marks getMarks() {
        return marks;
    }

    public void setMarks(Marks marks) {
        this.marks = marks;
    }

    public void setSubject(Subject subject){
        this.id = subject.getId();
        this.name = subject.getName();
        this.professorId = subject.getProfessorId();
        this.marks = subject.getMarks();
    }
}
