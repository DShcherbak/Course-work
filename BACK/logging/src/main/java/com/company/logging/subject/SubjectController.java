package com.company.logging.subject;

import com.company.logging.group.Group;
import com.company.logging.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/subject")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService){
        this.subjectService = subjectService;
    }

    @GetMapping
    public List<Subject> getSubjects(@RequestParam(name = "id", required = false) Long id,
                                     @RequestParam(name = "name", required = false) String name,
                                     @RequestParam(name = "professorId", required = false) Long professorId){
        if(id != null){
            return subjectService.getSubjectById(id);
        } else if (professorId != null) {
            return subjectService.getSubjectByProfessorId(professorId);
        } else if (name != null) {
            return subjectService.getSubjectByName(name);
        } else {
            return subjectService.getSubjects();
        }
    }

    @PostMapping
    public void registerSubject(@RequestBody Subject subject){
        subjectService.addNewSubject(subject);
    }

    @PatchMapping
    public void updateSubject(@RequestParam(name = "id", required = false) Long oldId,
                            @RequestBody Subject subject){
        subjectService.updateSubject(oldId, subject);
    }

    @DeleteMapping
    public void deleteSubject(@RequestParam(name = "id") Long id){
        subjectService.deleteSubjectById(id);
    }
}