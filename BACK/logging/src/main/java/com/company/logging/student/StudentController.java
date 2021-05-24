package com.company.logging.student;

import com.company.logging.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(@RequestParam(name = "id", required = false) Long id,
                                     @RequestParam(name = "name", required = false) String name){
        if(id != null){
            return studentService.getStudentById(id);
        } else if (name != null){
            return studentService.getStudentByName(name);
        } else {
            return studentService.getStudents();
        }
    }

    @PostMapping
    public void registerStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @PatchMapping
    public void updateStudent(@RequestParam(name = "id") Long oldId,
                              @RequestBody Student student){
        studentService.updateStudent(oldId, student);
    }

    @DeleteMapping
    public void deleteStudent(@RequestParam(name = "id") Long id){
        studentService.deleteStudentById(id);
    }
}
