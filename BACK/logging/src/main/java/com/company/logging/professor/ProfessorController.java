package com.company.logging.professor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/professor")
public class ProfessorController {

    private final ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService){
        this.professorService = professorService;
    }
    @GetMapping
    public List<Professor> getProfessors(@RequestParam(name = "id", required = false) Long id,
                                 @RequestParam(name = "name", required = false) String name){
        if(id != null){
            return professorService.getProfessorById(id);
        } else if (name != null) {
            return professorService.getProfessorByName(name);
        }   else {
            return professorService.getProfessors();
        }
    }

    @PatchMapping(path = "changeRole")
    public void changeRoleProfessor(@RequestParam(name = "id") Long professorId,
                                 @RequestParam(name = "role") String newRole){
        professorService.resetProfessorsRole(professorId, newRole);
    }

    @GetMapping(path = "requests")
    public List<Professor> getProfessorRequests(){
        return professorService.getProfessorRequests();
    }

    @PostMapping
    public void registerProfessor(@RequestBody Professor professor){
        professorService.addNewProfessor(professor);
    }

    @PatchMapping
    public void updateProfessor(@RequestParam(name = "id", required = false) Long oldId,
                            @RequestBody Professor professor){
        professorService.updateProfessor(oldId, professor);
    }

    @DeleteMapping
    public void deleteProfessor(@RequestParam(name = "id") Long id){
        professorService.deleteProfessorById(id);
    }
}
