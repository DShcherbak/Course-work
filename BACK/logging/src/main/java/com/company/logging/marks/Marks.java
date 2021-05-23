package com.company.logging.marks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Marks {
    List<String> columnsNames = new ArrayList<>();
    Map<Long, List<Long>> studentsMarks = new HashMap<>();

    public Marks(List<String> columnsNames, Map<Long, List<Long>> studentsMarks){
        this.columnsNames = columnsNames;
        this.studentsMarks = studentsMarks;
    }

    public Marks() {
    }
}
