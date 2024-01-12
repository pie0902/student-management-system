package org.example.camp.K.model;

import java.util.Arrays;
import java.util.List;

public class Score {
    private String StudentId; // 학생 ID를 받아서
    private String subtitle; // 과목
    private int round; // 몇회차
    private int score; // 점수
    private String grade; // 점수 등급



    //    public Score(String seq) {
//        this.scoreId = seq;
//    }
    // Getter



    public Score(String StudentId, String subtitle, int round, int score, String grade) {
        this.StudentId = StudentId;
        this.subtitle = subtitle;
        this.round = round;
        this.score = score;
        this.grade = grade;
    }


}
