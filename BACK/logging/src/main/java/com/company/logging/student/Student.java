package com.company.logging.student;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private Long groupId;
    private Long startingYear;

    public Student(String name, String email, Long groupId, Long startingYear) {
        this.name = name;
        this.email = email;
        this.groupId = groupId;
        this.startingYear = startingYear;
    }

    public Student() {

    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", groupId=" + groupId +
                ", startingYear=" + startingYear +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(name, student.name) && Objects.equals(email, student.email) && Objects.equals(groupId, student.groupId) && Objects.equals(startingYear, student.startingYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, groupId, startingYear);
    }

    public Student(Long id, String name, String email, Long groupId, Long startingYear) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.groupId = groupId;
        this.startingYear = startingYear;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getStartingYear() {
        return startingYear;
    }

    public void setStartingYear(Long startingYear) {
        this.startingYear = startingYear;
    }

    public void setStudent(Student student){
        this.id = student.getId();
        this.name = student.getName();
        this.email = student.getEmail();
        this.groupId = student.getGroupId();
        this.startingYear = student.getStartingYear();
    }
}
