package org.example.camp.model;

public class Subject {
    private int subjectId;
    private String subjectName;
    private String subjectType;

    public Subject(int seq, String subjectName, String subjectType) {
        this.subjectId = seq;
        this.subjectName = subjectName;
        this.subjectType = subjectType;
    }

    // Getter
    public int getSubjectId() {
        return subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectType() {
        return subjectType;
    }

    @Override
    public String toString() {
        return subjectId +" "+subjectName+ " "+subjectType;
    }
}
