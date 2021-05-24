package com.company.logging.marks;

import com.company.logging.auxiliary.TaskNames;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Marks {
    Long subjectId;
    List<String> columnsNames = new ArrayList<>();
    Map<Long, ArrayList<Double>> studentsMarks = new HashMap<>();

    public List<String> getColumnsNames() {
        return columnsNames;
    }

    public void setColumnsNames(List<String> columnsNames) {
        this.columnsNames = columnsNames;
    }

    public Map<Long, ArrayList<Double>> getStudentsMarks() {
        return studentsMarks;
    }

    public void setStudentsMarks(HashMap<Long, ArrayList<Double>> studentsMarks) {
        this.studentsMarks = studentsMarks;
    }

    @Transient
    public ArrayList<TaskNames> getTaskNames(){
        ArrayList<TaskNames> taskNames = new ArrayList<>();
        for(int i = 0; i < columnsNames.size(); i++){
            taskNames.add(new TaskNames(subjectId, columnsNames.get(i), (long) i+1));
        }
        return taskNames;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Marks(Long subjectId, List<String> columnsNames, Map<Long, ArrayList<Double>> studentsMarks){
        this.subjectId = subjectId;
        this.columnsNames = columnsNames;
        this.studentsMarks = studentsMarks;
    }

    public Marks(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Marks() {
    }
}
