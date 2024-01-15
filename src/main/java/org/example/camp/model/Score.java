package org.example.camp.model;

public class Score {
    private String studentId;
    private int subjectId;

    private int round;
    private int score;

    public Score(String studentId, int subjectId, int round, int score) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.round = round;
        this.score = score;
    }

    public Score() {

    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getStudentId() {
        return studentId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public int getRound() {
        return round;
    }

    public int getScore() {
        return score;
    }
}
