package org.example.camp.model;

import java.util.List;

public class Student {
    private String studentId;
    private String studentName;

    private String[] statusArr = {"좋음","보통","요주인물"};
    private String status;
    public Student(String seq, String studentName,int statusNum) {
        this.studentId = seq;
        this.studentName = studentName;
        this.status = statusArr[statusNum];
    }

    // Getter
    public String getStudentId() {
        return studentId;
    }
    public String getStudentName() {
        return studentName;
    }
    public String getStatus(){
        return status;
    }

<<<<<<< HEAD
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
=======
    @Override
    public String toString() {
        return studentId + studentName;
>>>>>>> dev
    }
}


