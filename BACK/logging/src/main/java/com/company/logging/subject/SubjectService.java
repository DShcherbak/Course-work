package com.company.logging.subject;

import com.company.logging.group.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository){
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getSubjects(){
        return subjectRepository.findAll();
    }

    public List<Subject> getSubjectById(Long id){
        Optional<Subject> o_subject = subjectRepository.findById(id);
        if(o_subject.isEmpty()){
            return new ArrayList<>();
        } else {
            Subject subject = o_subject.get();
            return List.of(subject);
        }
    }

    public List<Subject> getSubjectByProfessorId(Long id){
        Optional<Subject> o_subject = subjectRepository.findByProfessorId(id);
        if(o_subject.isEmpty()){
            return new ArrayList<>();
        } else {
            Subject subject = o_subject.get();
            return List.of(subject);
        }
    }

    public List<Subject> getSubjectByName(String name){
        Optional<Subject> o_subject = subjectRepository.findByName(name);
        if(o_subject.isEmpty()){
            return new ArrayList<>();
        } else {
            Subject subject = o_subject.get();
            return List.of(subject);
        }
    }

    public void addNewSubject(Subject subject){
        subjectRepository.save(subject);
    }

    public void updateSubject(Long oldId, Subject subject){
        var o_subject = subjectRepository.findById(oldId);
        if(o_subject.isPresent()){
            var oldSubject = o_subject.get();
            oldSubject.setSubject(subject);
        } else {
            throw new IllegalStateException("No group with id : " + oldId);
        }
    }

    public void deleteSubjectById(Long id){
        var exists = subjectRepository.existsById(id);
        if(exists){
            subjectRepository.deleteById(id);
        } else {
            throw new IllegalStateException("No subject with such id");
        }
    }
}
