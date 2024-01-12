package org.example.camp.model;

import java.util.List;

public class Student {
    private String studentId;
    private String studentName;

    public Student(String seq, String studentName) {
        this.studentId = seq;
        this.studentName = studentName;
    }

    // Getter
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setMandatorySubjects(List<Subject> mandatorySubjects) {
    }

    public void setChoiceSubjects(List<Subject> choiceSubjects) {
    }

    public void addScore(Score score) {
    }

    public Subject[] getMandatorySubjects() {
        return new Subject[0];
    }

    public Subject[] getChoiceSubjects() {
        return new Subject[0];
    }
}


