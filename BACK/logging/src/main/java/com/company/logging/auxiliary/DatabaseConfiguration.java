package com.company.logging.auxiliary;

import com.company.logging.group.Group;
import com.company.logging.group.GroupRepository;
import com.company.logging.professor.Professor;
import com.company.logging.professor.ProfessorRepository;
import com.company.logging.student.Student;
import com.company.logging.student.StudentRepository;
import com.company.logging.subject.Subject;
import com.company.logging.subject.SubjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DatabaseConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository,
                                        GroupRepository groupRepository,
                                        SubjectRepository subjectRepository,
                                        ProfessorRepository professorRepository,
                                        TaskNamesRepository taskNamesRepository,
                                        StudentSubjectMarksRepository studentSubjectMarksRepository){
        return args -> {
            Student Den = new Student("Denys", "@gmail", 1L, 2018L);
            Student Max = new Student("Max", "@gmail", 1L, 2018L);
            Student Alex = new Student("Alex", "@gmail", 1L, 2018L);
            studentRepository.saveAll(List.of(Den, Max, Alex));

            Group ips = new Group("IPS-31");
            Group k28 = new Group("K-28");
            groupRepository.saveAll(List.of(ips, k28));

            Subject oop = new Subject("OOP", 1L);
            Subject spos = new Subject("SPOS", 2L);
            Subject za = new Subject("ZA", 3L);
            Subject moog = new Subject("MOOG", 3L);
            subjectRepository.saveAll(List.of(oop, spos, za, moog));

            Professor jereb = new Professor("K.A.Jereb", "jereb@gmail","PROFESSOR");
            Professor chen = new Professor("O.I.Chentzov", "chentsov@gmail","PROFESSOR");
            Professor linder = new Professor("J.M.Linder", "linder@gmail","PROFESSOR");
            Professor prov = new Professor("A.I.Provotar", "aprowata@bigmir","UNKNOWN");
            Professor kashpur = new Professor("O.F.Kashpur", "kashpur@gmail","ADMIN");
            professorRepository.saveAll(List.of(jereb, linder, prov, kashpur));

            ArrayList<TaskNames> taskNames = new ArrayList<>();
            taskNames.add(new TaskNames(1L,"Lab 1",1L));
            taskNames.add(new TaskNames(1L,"Lab 2",2L));
            taskNames.add(new TaskNames(1L,"Lab 3",3L));
            taskNames.add(new TaskNames(2L,"Lab 1",1L));
            taskNames.add(new TaskNames(2L,"Lab 2",3L));
            taskNames.add(new TaskNames(2L,"Module 1",2L));
            taskNames.add(new TaskNames(2L,"Module 2",4L));
            taskNames.add(new TaskNames(3L,"Lab",1L));
            taskNames.add(new TaskNames(3L,"Колоквіум",2L));
            taskNamesRepository.saveAll(taskNames);

            ArrayList<StudentSubjectMarks> ssm = new ArrayList<>();
            ssm.add(new StudentSubjectMarks(1L,1L,1L,18.5));
            ssm.add(new StudentSubjectMarks(1L,1L,2L,20.0));
            ssm.add(new StudentSubjectMarks(1L,1L,3L,20.0));
            ssm.add(new StudentSubjectMarks(2L,1L,1L,15.5));
            ssm.add(new StudentSubjectMarks(2L,1L,2L,15.0));
            ssm.add(new StudentSubjectMarks(2L,1L,3L,15.0));
            ssm.add(new StudentSubjectMarks(3L,1L,1L,0.0));
            ssm.add(new StudentSubjectMarks(3L,1L,2L,12.0));
            ssm.add(new StudentSubjectMarks(3L,1L,3L,15.0));

            ssm.add(new StudentSubjectMarks(1L,2L,1L,18.5));
            ssm.add(new StudentSubjectMarks(1L,2L,2L,20.0));
            ssm.add(new StudentSubjectMarks(1L,2L,3L,20.0));
            ssm.add(new StudentSubjectMarks(1L,2L,4L,20.0));
            ssm.add(new StudentSubjectMarks(2L,2L,1L,15.5));
            ssm.add(new StudentSubjectMarks(2L,2L,2L,15.0));
            ssm.add(new StudentSubjectMarks(2L,2L,3L,15.0));
            ssm.add(new StudentSubjectMarks(2L,2L,4L,20.0));
            ssm.add(new StudentSubjectMarks(3L,2L,1L,0.0));
            ssm.add(new StudentSubjectMarks(3L,2L,2L,12.0));
            ssm.add(new StudentSubjectMarks(3L,2L,3L,15.0));
            ssm.add(new StudentSubjectMarks(3L,2L,3L,20.0));
            studentSubjectMarksRepository.saveAll(ssm);
        };
    }
}
