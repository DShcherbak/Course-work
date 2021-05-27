package com.company.logging.group;

import com.company.logging.student.Student;
import com.company.logging.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/group")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService){
        this.groupService = groupService;
    }

    @GetMapping
    public List<Group> getGroups(@RequestParam(name = "id", required = false) Long id,
                                 @RequestParam(name = "name", required = false) String name){
        if(id != null){
            return groupService.getGroupById(id);
        } else if (name != null) {
            return groupService.getGroupByName(name);
        }   else {
            return groupService.getGroups();
        }
    }

    @PostMapping
    public void registerGroup(@RequestBody Group group){
        groupService.addNewGroup(group);
    }

    @PatchMapping
    public void updateGroup(@RequestParam(name = "id", required = false) Long oldId,
                            @RequestBody Group group){
        groupService.updateGroup(oldId, group);
    }

    @DeleteMapping
    public void deleteGroup(@RequestParam(name = "id") Long id){
        groupService.deleteGroupById(id);
    }
}
