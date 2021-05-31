package com.company.logging.subject;

import com.company.logging.auxiliary.*;
import com.company.logging.marks.Marks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final StudentSubjectMarksRepository studentSubjectMarksRepository;
    private final TaskNamesRepository taskNamesRepository;
    private final SubjectGroupRepository subjectGroupRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository,
                          StudentSubjectMarksRepository studentSubjectMarksRepository,
                          TaskNamesRepository taskNamesRepository,
                          SubjectGroupRepository subjectGroupRepository){
        this.subjectRepository = subjectRepository;
        this.studentSubjectMarksRepository = studentSubjectMarksRepository;
        this.taskNamesRepository = taskNamesRepository;
        this.subjectGroupRepository = subjectGroupRepository;
    }

    public List<Subject> getSubjects(){
        var subjects =  subjectRepository.findAll();
        for(var subject : subjects){
            completeSubject(subject);
        }
        return subjects;
    }

    public List<Subject> getSubjectById(Long id){
        Optional<Subject> o_subject = subjectRepository.findById(id);
        if(o_subject.isEmpty()){
            return new ArrayList<>();
        } else {
            Subject subject = o_subject.get();
            completeSubject(subject);
            return List.of(subject);
        }
    }

    public List<Subject> getSubjectByProfessorId(Long id){
        List<Subject> subjects = subjectRepository.findAllByProfessorId(id);
        for(var subject : subjects){
            completeSubject(subject);
        }
        return subjects;
    }

    public List<Subject> getSubjectByGroupId(Long id){
        List<Subject> subjects = subjectRepository.findAllByGroupId(id);
        for(var subject : subjects){
            completeSubject(subject);
        }
        return subjects;
    }

    public List<Subject> getSubjectByName(String name){
        Optional<Subject> o_subject = subjectRepository.findByName(name);
        if(o_subject.isEmpty()){
            return new ArrayList<>();
        } else {
            Subject subject = o_subject.get();
            completeSubject(subject);
            return List.of(subject);
        }
    }

    public void addNewSubject(Subject subject){
        subjectRepository.save(subject);
        saveMarks(subject);
    }

    public void updateSubject(Long oldId, Subject subject){
        var o_subject = subjectRepository.findById(oldId);
        if(o_subject.isPresent()){
            var oldSubject = o_subject.get();
            if(subject.getId() == null){
                subject.setId(oldId);
            }
            oldSubject.setSubject(subject);
            saveMarks(subject);
            saveGroups(subject);
            subjectRepository.save(subject);
        } else {
            throw new IllegalStateException("No group with id : " + oldId);
        }
    }

    public void deleteSubjectById(Long id){
        var exists = subjectRepository.existsById(id);
        if(exists){
            subjectRepository.deleteById(id);
            studentSubjectMarksRepository.deleteAllBySubjectId(id);
        } else {
            throw new IllegalStateException("No subject with such id");
        }
    }

    private void completeSubject(Subject subject){
        getMarks(subject);
        getGroups(subject);
    }


    private void saveMarks(Subject subject){

        taskNamesRepository.deleteAllBySubjectId(subject.getId());
        Marks marks = subject.getMarks();
        ArrayList<TaskNames> taskNames = marks.getTaskNames();
        taskNamesRepository.saveAll(taskNames);


        studentSubjectMarksRepository.deleteAllBySubjectId(subject.getId());

        ArrayList<StudentSubjectMarks> studentSubjectMarks = new ArrayList<>();
        var marksDictionary = marks.getStudentsMarks();
        for(var student : marksDictionary.keySet()){
            var studentsMarks = marksDictionary.get(student);
            for(int i = 0; i < studentsMarks.size(); i++){
                studentSubjectMarks.add(new StudentSubjectMarks(student,subject.getId(), (long) i+1, studentsMarks.get(i)));
            }
        }

        studentSubjectMarksRepository.saveAll(studentSubjectMarks);
    }

    public void getMarks(Subject subject){
        Marks marks = new Marks(subject.getId());
        ArrayList<String> columnsNames = new ArrayList<>();
        var taskNames = taskNamesRepository.getBySubjectIdOrderedByNumber(subject.getId());
        for(var task : taskNames){
            columnsNames.add(task.getName());
        }
        marks.setColumnsNames(columnsNames);


        HashMap<Long, ArrayList<Double>> studentMarks = new HashMap<>();
        var students = studentSubjectMarksRepository.getStudentBySubjectId(subject.getId());
        var allMarksArray = studentSubjectMarksRepository.getMarksBySubject(subject.getId());
        for(var studentId : students){
            var currentStudentMarks = (ArrayList<Double>) allMarksArray.stream()
                    .filter(ssm -> ssm.getStudentId().equals(studentId))
                    .sorted(Comparator.comparing(StudentSubjectMarks::getNumber))
                    .map(StudentSubjectMarks::getMark)
                    .collect(Collectors.toList());
            studentMarks.put(studentId,currentStudentMarks);
        }
        marks.setStudentsMarks(studentMarks);

        subject.setMarks(marks);
    }

    public void saveGroups(Subject subject){
        subjectGroupRepository.deleteAllBySubjectId(subject.getId());
        ArrayList<SubjectGroup> subjectGroups = (ArrayList<SubjectGroup>) subject.getGroupIds().stream()
                .map(id -> new SubjectGroup(subject.getId(), id)).collect(Collectors.toList());
        subjectGroupRepository.saveAll(subjectGroups);
    }
    
    public void getGroups(Subject subject){
        ArrayList<Long> subjectGroups = (ArrayList<Long>) subjectGroupRepository.selectGroupsBySubjectId(subject.getId());
        subject.setGroupIds(subjectGroups);
    }

}
