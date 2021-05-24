package com.company.logging.group;

import com.company.logging.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository, StudentRepository studentRepository){
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
    }

    public List<Group> getGroups(){
        List<Group> groups =  groupRepository.findAll();
        for(var group : groups){
            enrichGroup(group);
        }
        return groups;
    }

    public List<Group> getGroupById(Long id){
        Optional<Group> o_group = groupRepository.findById(id);
        if(o_group.isEmpty()){
            return new ArrayList<>();
        } else {
            Group group = o_group.get();
            enrichGroup(group);
            return List.of(group);
        }
    }

    public List<Group> getGroupByName(String name){
        Optional<Group> o_group = groupRepository.findByName(name);
        if(o_group.isEmpty()){
            return new ArrayList<>();
        } else {
            Group group = o_group.get();
            enrichGroup(group);
            return List.of(group);
        }
    }

    public void addNewGroup(Group group){
        groupRepository.save(group);
    }

    public void updateGroup(Long oldId, Group group){
        var o_group = groupRepository.findById(oldId);
        if(o_group.isPresent()){
            var oldGroup = o_group.get();
            if(group.getId() == null){
                group.setId(oldId);
            }
            oldGroup.setGroup(group);
            groupRepository.save(group);
        } else {
            throw new IllegalStateException("No group with id : " + oldId);
        }
    }

    public void deleteGroupById(Long id){
        var exists = groupRepository.existsById(id);
        if(exists){
            groupRepository.deleteById(id);
        } else {
            throw new IllegalStateException("No group with id : " + id);
        }
    }

    private void enrichGroup(Group group){
        var students = studentRepository.findAll();
        System.out.println(students);
        students.stream()
                .filter(student->student.getGroupId().equals(group.getId()))
                .forEach(group::addStudent);
    }
}
