package com.company.logging.auxiliary;

import javax.persistence.*;

@Entity
@Table
public class SubjectGroup {
    @Id
    @SequenceGenerator(
            name = "sg_sequence",
            sequenceName = "sg_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sg_sequence"
    )
    Long id;
    Long subjectId;
    Long groupId;

    public SubjectGroup() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public SubjectGroup(Long subjectId, Long groupId) {
        this.subjectId = subjectId;
        this.groupId = groupId;
    }

    public SubjectGroup(Long id, Long subjectId, Long groupId) {
        this.id = id;
        this.subjectId = subjectId;
        this.groupId = groupId;
    }
}
