package com.company.logging.student;

import com.company.logging.group.Group;
import com.company.logging.group.GroupRepository;
import com.company.logging.subject.Subject;
import com.company.logging.subject.SubjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class StudentConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, GroupRepository groupRepository, SubjectRepository subjectRepository){
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
        };
    }
}
