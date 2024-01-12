package org.example.camp;


import org.example.camp.K.CreateScore;
import org.example.camp.Y.CreateStudent;
import org.example.camp.model.Score;
import org.example.camp.model.Student;
import org.example.camp.model.Subject;
import java.util.*;
import java.util.List;

/**
 * Notification
 * Java, 객체지향이 아직 익숙하지 않은 분들은 위한 소스코드 틀입니다.
 * main 메서드를 실행하면 프로그램이 실행됩니다.
 * model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요!
 * 프로젝트 구조를 변경하거나 기능을 추가해도 괜찮습니다!
 * 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
 */
// 일단    Map<학생 ID, List<과목ID>>       로 한 학생당 어떤 과목 수강하는지 관리하는 리스트 만들기
// 과목 ID를 고정시키기
// 점수 관리해주기 Map<학생ID,Map<과목ID,List<점수>>
public class CampManagementApplication {
    private static Map<String,List<Subject>> management; //Map<학생 ID, List<과목ID>>
    // 데이터 저장소

    //학생관리
    private static HashMap<String,Student> studentStore;

    private static List<Score> ScoreStore;
    // 과목 타입
    private static String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static String SUBJECT_TYPE_CHOICE = "CHOICE";
    // index 관리 필드
    private static int studentIndex;
    private static final String INDEX_TYPE_STUDENT = "ST";


    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        setInitData();
        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println(e + "\n오류 발생!\n프로그램을 종료합니다.");
        }
    }
    public static void setStudentStore(String key) {
        studentStore.get(key);
    }
    public static Map<String,List<Subject>> getManagement(){
        return management;
    }
    public static void setManagement(String string,List<Subject> subjectList) {
        management.put(string,subjectList);
    }
    public static void setStudentStore(String studentId,Student student){
        studentStore.put(studentId,student);
    }



    // 초기 데이터 생성
    private static void setInitData() {
        management = new HashMap<>();
        studentStore = new HashMap<>();
        ScoreStore = new ArrayList<>();
    }


    public static String getSequence() {
        studentIndex++;
        return INDEX_TYPE_STUDENT + studentIndex;
    }
    private static void displayMainView() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> displayStudentView(); // 수강생 관리
                case 2 -> displayScoreView(); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
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
            sc.nextLine();
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
        CreateStudent createStudent = new CreateStudent();
        createStudent.mkStudent();
    }
    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        if (!management.isEmpty()) {
            System.out.println("\n현재 등록된 수강생:");
            for (Map.Entry<String, List<Subject>> entry : management.entrySet()) {
                String studentId = entry.getKey();
                Student student = studentStore.get(studentId);
                List<Subject> subjects = entry.getValue();
                System.out.println("학생 ID: " + studentId);
                System.out.println("학생 이름: " + student.getStudentName());
                System.out.println("수강 과목:");
                for (Subject subject : subjects) {
                    System.out.println(subject);
                }
                System.out.println();
            }
        } else {
            System.out.println("\n등록된 수강생이 없습니다.");
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

    private static String inputStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next();
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    private static String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next();
    }
    private static int getsubjectId() {
        System.out.print("\n과목을 입력해 주세요");
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
    // 수강생의 과목별 시험 회차 및 점수 등록

    //민규님
   private static void createScore() {
            CreateScore score = new CreateScore();
            score.checkScore();
            ScoreStore.add(score.getScoreStore());
   }

    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {
        String studentId = inputStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (수정할 과목 및 회차, 점수)
        System.out.println("시험 점수를 수정합니다...");
        // 기능 구현
        System.out.println("\n점수 수정 성공!");
    }

    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        String studentId = inputStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (조회할 특정 과목)
        System.out.println("회차별 등급을 조회합니다...");
        // 기능 구현
        System.out.println("\n등급 조회 성공!");
    }

}
