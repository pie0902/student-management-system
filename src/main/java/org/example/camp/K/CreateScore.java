package org.example.camp.K;

import org.example.camp.CampManagementApplication;
import org.example.camp.model.Score;

import java.util.List;
import java.util.Scanner;

public class CreateScore {
    Score scoreStore;
    private static Scanner sc = new Scanner(System.in);

    // 수강생의 과목별 시험 회차 및 점수 등록
    public CreateScore() {

    }

    public Score checkScore() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        System.out.println(studentId + " 번 학생의 점수 등록을 시작합니다");
        int subjectId = getsubjectId();    // 과목
        int round = getRound();         //회차

        for (Score Id : CampManagementApplication.getScoreStore()) {
            if (studentId.equals(Id.getStudentId())) {
                if (subjectId == Id.getSubjectId()) {
                    if (round == Id.getRound()) {
                        System.out.println("이미 입력한 회차 입니다.");
                    }
                }
            }
        }
        int score = getScore();         //점수
        scoreStore = new Score(studentId, subjectId, round, score);
        System.out.println("시험 점수를 등록합니다...");

        // 기능 구현
        System.out.println("\n점수 등록 성공!");
        return scoreStore;

    }

    public Score getScoreStore() {
        return scoreStore;
    }

    private String getStudentId() {
        System.out.print("\n관리할 수강생의 아디이값을 입력하시오...");
        return sc.next();
    }

    private int getsubjectId() {
        System.out.println("\n과목의 번호를 입력해 주세요");
        System.out.println("[필수]\n1.Java 2.객체지향 3.Spring 4.JPA 5.MySQL" +
                "\n[선택]\n6.디자인 패턴 7.Spring 8.SecurityRedis 9.MongoDB");
        return sc.nextInt();
    }

    private int getRound() {
        System.out.print("\n회차를 입력해 주세요");
        return sc.nextInt();
    }

    private int getScore() {
        System.out.print("\n점수를 입력해 주세요");
        return sc.nextInt();
    }
}