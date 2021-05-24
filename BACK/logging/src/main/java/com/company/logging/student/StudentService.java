package com.company.logging.student;

import com.company.logging.group.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public List<Student> getStudentById(Long id){
        Optional<Student> o_student = studentRepository.findById(id);
        if(o_student.isEmpty()){
            return new ArrayList<>();
        } else {
            Student student = o_student.get();
            return List.of(student);
        }
    }

    public List<Student> getStudentByName(String name){
        Optional<Student> o_student = studentRepository.findByName(name);
        if(o_student.isEmpty()){
            return new ArrayList<>();
        } else {
            Student student = o_student.get();
            return List.of(student);
        }
    }

    public void addNewStudent(Student student){
        studentRepository.save(student);
    }

    public void updateStudent(Long oldId, Student student){
        var o_student = studentRepository.findById(oldId);
        if(o_student.isPresent()){
            var oldStudent = o_student.get();
            if(student.getId() == null){
                student.setId(oldId);
            }
            oldStudent.setStudent(student);
            studentRepository.save(oldStudent);
        } else {
            throw new IllegalStateException("No group with id : " + oldId);
        }
    }

    public void deleteStudentById(Long id){
        var exists = studentRepository.existsById(id);
        if(exists){
            studentRepository.deleteById(id);
        } else {
            throw new IllegalStateException("No student with such id");
        }
    }
}
