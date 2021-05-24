package com.company.logging.professor;

import com.company.logging.professor.Professor;
import com.company.logging.professor.ProfessorRepository;
import com.company.logging.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository, StudentRepository studentRepository){
        this.professorRepository = professorRepository;
        this.studentRepository = studentRepository;
    }

    public List<Professor> getProfessors(){
        return professorRepository.findAll();
    }

    public List<Professor> getProfessorById(Long id){
        Optional<Professor> o_professor = professorRepository.findById(id);
        if(o_professor.isEmpty()){
            return new ArrayList<>();
        } else {
            Professor professor = o_professor.get();
            return List.of(professor);
        }
    }

    public List<Professor> getProfessorByName(String name){
        Optional<Professor> o_professor = professorRepository.findByName(name);
        if(o_professor.isEmpty()){
            return new ArrayList<>();
        } else {
            Professor professor = o_professor.get();
            return List.of(professor);
        }
    }

    public void addNewProfessor(Professor professor){
        professor.setRole("UNKNOWN");
        professorRepository.save(professor);
    }

    public void updateProfessor(Long oldId, Professor professor){
        var o_professor = professorRepository.findById(oldId);
        if(o_professor.isPresent()){
            var oldProfessor = o_professor.get();
            if(professor.getId() == null){
                professor.setId(oldId);
            }
            oldProfessor.setProfessor(professor);
            professorRepository.save(oldProfessor);
        } else {
            throw new IllegalStateException("No professor with id : " + oldId);
        }
    }

    public void resetProfessorsRole(Long professorId, String role){
        switch (role){
            case "DECLINED" -> {
                var o_professor = professorRepository.findById(professorId);
                if(o_professor.isPresent()) {
                    var professor = o_professor.get();
                    if(professor.getRole().equals("UNKNOWN")) {
                        professorRepository.deleteById(professorId);
                        professorRepository.save(professor);
                    }
                }
            }
            case "ACCEPTED" -> {
                var o_professor = professorRepository.findById(professorId);
                if(o_professor.isPresent()) {
                    var professor = o_professor.get();
                    professor.setRole("PROFESSOR");
                    professorRepository.save(professor);
                }
            }
            case "ADMIN" -> {
                var o_professor = professorRepository.findById(professorId);
                if(o_professor.isPresent()) {
                    var professor = o_professor.get();
                    professor.setRole("ADMIN");
                    professorRepository.save(professor);
                }
            }
        }
    }

    public void deleteProfessorById(Long id){
        var exists = professorRepository.existsById(id);
        if(exists){
            professorRepository.deleteById(id);
        } else {
            throw new IllegalStateException("No professor with id : " + id);
        }
    }

    public List<Professor> getProfessorRequests() {
        return professorRepository.getAllProfessorRequests();
    }
}
