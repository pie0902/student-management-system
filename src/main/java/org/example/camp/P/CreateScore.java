package org.example.camp.P;

import org.example.camp.model.Score;

import java.util.List;
import java.util.Scanner;

public class CreateScore {
        Score scoreStore;
        private static Scanner sc = new Scanner(System.in);
    // 수강생의 과목별 시험 회차 및 점수 등록
        public CreateScore() {

        }
        public void checkScore(){
            String studentId = getStudentId(); // 관리할 수강생 고유 번호
            System.out.println(studentId + " 번 학생의 점수 등록을 시작합니다");
            int subjectId = getsubjectId();    // 과목
            int round = getRound();         //회차
            int score = getScore();         //점수
            if(round>=11 || 0 >= round) {
                System.out.println("회차 값을 잘못 입력하였습니다.");
                return;
            }
            if (score>100 || 0> score ){
                System.out.println("점수 값을 잘못 입력하였습니다");
                return;
            }
            scoreStore = new Score(studentId, subjectId, round, score);
            System.out.println(getStudentId() +"번 학생의 " + getsubjectId() + "번 과목의 " + getRound() + "회차 점수는 " + getScore() + "점 입니다.");
            System.out.println("시험 점수를 등록합니다...");

            // 기능 구현
            System.out.println("\n점수 등록 성공!");

        }

    public Score getScoreStore(){
            return scoreStore;
    }
    private static String getStudentId() {
        System.out.print("\n관리할 수강생의 아디이값을 입력하시오...");
        return sc.next();
    }
    private static int getsubjectId() {
        System.out.println("\n과목의 번호를 입력해 주세요");
        System.out.println("[필수]\n1.Java 2.객체지향 3.Spring 4.JPA 5.MySQL"+
                "\n[선택]\n6.디자인 패턴 7.Spring 8.SecurityRedis 9.MongoDB");
        return sc.nextInt();
    }
    private static int getRound() {
        System.out.print("\n회차를 입력해 주세요");
        return sc.nextInt();
    }
    private static int getScore() {
        System.out.print("\n점수를 입력해 주세요");
        return sc.nextInt();
    }
}
