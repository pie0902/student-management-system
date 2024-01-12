package org.example.camp.K;

import org.example.camp.CampManagementApplication;
import org.example.camp.model.Score;

import java.util.List;
import java.util.Scanner;

public class CreateScore {
    CampManagementApplication cm = new CampManagementApplication();
        Score scoreStore = new Score();
        private static Scanner sc = new Scanner(System.in);
    // 수강생의 과목별 시험 회차 및 점수 등록
        public CreateScore() {

        }
        public Score checkScore(){
            try {
                String studentId = getStudentId(); // 관리할 수강생 고유 번호
                System.out.println(studentId + " 번 학생의 점수 등록을 시작합니다");
                int subjectId = getsubjectId();    // 과목
                int round = getRound();         //회차
                for (Score Id : CampManagementApplication.getScoreStore()) {
                    if (studentId.equals(Id.getStudentId())) {
                        if (subjectId == Id.getSubjectId()) {
                            if (round == Id.getRound()) {
                                System.out.println("이미 입력한 회차 입니다.");
                                throw new Exception();
                            }
                        }
                    }
                }
                if (round >= 11 || 0 >= round) {
                    System.out.println("회차 값을 잘못 입력하였습니다.");
                    throw new Exception();

                }
                int score = getScore();         //점수

                if (score > 100 || 0 > score) {
                    System.out.println("점수 값을 잘못 입력하였습니다");
                    throw new Exception();

                }

                scoreStore = new Score(studentId, subjectId, round, score);


                System.out.println("시험 점수를 등록합니다...");

                // 기능 구현
                System.out.println("\n점수 등록 성공!");

            }catch (Exception e){
                System.out.println("입력화면으로 돌아갑니다");
            }
            return scoreStore;
        }

    public Score getScoreStore(){
            return scoreStore;
    }
    private static String getStudentId() {
        System.out.print("\n관리할 수강생의 아이디를 입력하시오...");
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
