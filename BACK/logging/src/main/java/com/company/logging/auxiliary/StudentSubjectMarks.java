package com.company.logging.auxiliary;

import javax.persistence.*;

@Entity
@Table
public class StudentSubjectMarks {
    @Id
    @SequenceGenerator(
            name = "ssm_sequence",
            sequenceName = "ssm_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ssm_sequence"
    )
    Long id;
    Long studentId;
    Long subjectId;
    Long number;
    Double mark;

    public StudentSubjectMarks() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public StudentSubjectMarks(Long id, Long studentId, Long subjectId, Long number, Double mark) {
        this.id = id;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.number = number;
        this.mark = mark;
    }

    public StudentSubjectMarks(Long studentId, Long subjectId, Long number, Double mark) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.number = number;
        this.mark = mark;
    }

    public StudentSubjectMarks(Long studentId, Long subjectId, Long number) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.number = number;
    }
}
