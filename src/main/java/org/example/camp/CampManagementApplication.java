package org.example.camp;
import org.example.camp.model.Student;
import org.example.camp.model.Score;
import org.example.camp.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CampManagementApplication {
    // 데이터 저장소
    private static List<Student> studentStore;
    private static List<Subject> subjectStore;
    private static List<Score> ScoreStore;

    // 과목 타입
    private static String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static String SUBJECT_TYPE_CHOICE = "CHOICE";

    // index 관리 필드
    private static int studentIndex;
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex;
    private static final String INDEX_TYPE_SUBJECT = "SU";
    private static int scoreIndex;
    private static final String INDEX_TYPE_SCORE = "SC";

    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        setInitData();
        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }

    // 초기 데이터 생성
    private static void setInitData() {
        studentStore = new ArrayList<>();
        subjectStore = List.of(
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Java",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "객체지향",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "JPA",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MySQL",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "디자인 패턴",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring Security",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Redis",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MongoDB",
                        SUBJECT_TYPE_CHOICE
                )
        );
        ScoreStore = new ArrayList<>();
    }

    // index 자동 증가
    private static String sequence(String type) {
        switch (type) {
            case INDEX_TYPE_STUDENT -> {
                studentIndex++;
                return INDEX_TYPE_STUDENT + studentIndex;
            }
            case INDEX_TYPE_SUBJECT -> {
                subjectIndex++;
                return INDEX_TYPE_SUBJECT + subjectIndex;
            }
            default -> {
                scoreIndex++;
                return INDEX_TYPE_SCORE + scoreIndex;
            }
        }
    }

    private static void displayMainView() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 수강생 등록");
            System.out.println("4. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> displayStudentView(); // 수강생 관리
                case 2 -> displayScoreView(); // 점수 관리
                case 3 -> createStudent(); // 수강생 등록
                case 4 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private static void displayStudentView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생 등록

    private static void createStudent() {
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next();

        // 필수 과목 등록
        List<Subject> mandatorySubjects = new ArrayList<>();
        for (Subject subject : subjectStore) {
            if (subject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY)) {
                System.out.print("수강할 필수 과목 '" + subject.getSubjectName() + "'을(를) 선택하세요 (Y/N): ");
                String choice = sc.next();
                if (choice.equalsIgnoreCase("Y")) {
                    mandatorySubjects.add(subject);
                }
            }
        }

        // 선택 과목 등록
        List<Subject> choiceSubjects = new ArrayList<>();
        for (Subject subject : subjectStore) {
            if (subject.getSubjectType().equals(SUBJECT_TYPE_CHOICE)) {
                System.out.print("수강할 선택 과목 '" + subject.getSubjectName() + "'을(를) 선택하세요 (Y/N): ");
                String choice = sc.next();
                if (choice.equalsIgnoreCase("Y")) {
                    choiceSubjects.add(subject);
                }
            }
        }

        // 수강생 인스턴스 생성
        Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName);
        student.setMandatorySubjects(mandatorySubjects);
        student.setChoiceSubjects(choiceSubjects);

        // 수강생을 등록된 수강생 목록에 추가
        studentStore.add(student);

        // 수강생 등록 결과 출력
        System.out.println("수강생 등록 성공!\n");
    }



    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");

        if (studentStore.isEmpty()) {
            System.out.println("등록된 수강생이 없습니다.");
        } else {
            System.out.println("등록된 수강생 목록:");

            for (Student student : studentStore) {
                System.out.println("학생 번호: " + student.getStudentId());
                System.out.println("학생 이름: " + student.getStudentName());
                System.out.println("------------------------------");
            }
        }

        System.out.println("\n수강생 목록 조회 성공!");
    }




    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    private static String getStudentId() {
        System.out.print("\n관리할 수강생의 아이디를 입력하시오...");
        return sc.next();
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    private static void createScore() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호

        // 해당 수강생이 등록된지 확인
        Student targetStudent = null;
        for (Student student : studentStore) {
            if (student.getStudentId().equals(studentId)) {
                targetStudent = student;
                break;
            }
        }

        if (targetStudent == null) {
            System.out.println("해당 ID를 가진 수강생을 찾을 수 없습니다.");
            return;
        }

        // 시험을 볼 과목 선택
        System.out.println("수강생이 시험을 보는 과목을 선택하세요:");
        for (Subject subject : targetStudent.getMandatorySubjects()) {
            System.out.println(subject.getSubjectName() + " (필수과목)");
        }
        for (Subject subject : targetStudent.getChoiceSubjects()) {
            System.out.println(subject.getSubjectName() + " (선택과목)");
        }

        System.out.print("과목명을 입력하세요: ");
        String subjectName = sc.next();

        // 선택한 과목에 대한 점수 입력
        System.out.print("시험 점수를 입력하세요: ");
        int scoreValue = sc.nextInt();

        // 점수 객체 생성 및 저장
        Score score = new Score(sequence(INDEX_TYPE_SCORE));
        score.setSubjectName(subjectName);
        score.setScoreValue(scoreValue);

        // 해당 수강생의 성적 목록에 추가
        targetStudent.addScore(score);

        // 전체 성적 목록에도 추가
        ScoreStore.add(score);

        System.out.println("점수 등록 성공!\n");
    }


    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (수정할 과목 및 회차, 점수)
        System.out.println("시험 점수를 수정합니다...");
        // 기능 구현
        System.out.println("\n점수 수정 성공!");
    }

    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (조회할 특정 과목)
        System.out.println("회차별 등급을 조회합니다...");
        // 기능 구현
        System.out.println("\n등급 조회 성공!");
    }

}
