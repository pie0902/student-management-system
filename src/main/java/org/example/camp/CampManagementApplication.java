package org.example.camp;


import org.example.camp.K.CreateScore;
import org.example.camp.Y.CreateStudent;
import org.example.camp.model.Score;
import org.example.camp.model.Student;
import org.example.camp.model.Subject;

import java.util.*;
import java.util.List;

/*
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
    private static Map<String, List<Subject>> management; //Map<학생 ID, List<과목ID>>
    // 데이터 저장소

    //학생관리
    private static HashMap<String, Student> studentStore;

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

    public static Map<String, List<Subject>> getManagement() {
        return management;
    }

    public static void setManagement(String string, List<Subject> subjectList) {
        management.put(string, subjectList);
    }

    public static void setStudentStore(String studentId, Student student) {
        studentStore.put(studentId, student);
    }

    public static List<Score> getScoreStore() {
        return ScoreStore;
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
            System.out.println("3. 상태별 수강생 조회");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();
            sc.nextLine();
            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> inquireStudentStatus(); // 상태별 수강생 목록 조회
                case 4 -> flag = false; // 메인 화면 이동
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

        ScoreStore.add(score.checkScore());
    }

    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        System.out.println("수정할 과목을 입력해 주십시오");
        System.out.println("1.Java  2.객체지향  3.Spring  4.JPA  5.MySQL  6.디자인패턴  7.Spring Security  8.Redis  9.MonggoDB  0.취소");
        sc.nextLine();
        int select = sc.nextInt();
        while (true) {
            if (select > 0 && select <= 9) {
                System.out.println("수정할 회차를 입력해 주십시오");
                break;
            } else if (select == 0) {
                displayScoreView();
                break;

            } else {
                System.out.println("잘못된 입력입니다");
            }
        }
        int round = sc.nextInt();
        while (true) {
            if (round > 0 && round <= 10) {
                System.out.println("수정할 점수를 입력해 주십시오");
                break;
            } else {
                System.out.println("잘못된 입력입니다");
            }
        }
        int score = sc.nextInt();
        while (true) {
            if (score > 0 && score <= 100) {
                break;
            } else {
                System.out.println("잘못된 입력입니다");
            }
        }
        System.out.println("시험 점수를 수정합니다...");

        //점수를 수정합니다
        for (int i = 0; i < ScoreStore.size(); i++) {
            if (studentId.equals(ScoreStore.get(i).getStudentId())) {
                if (select == ScoreStore.get(i).getSubjectId() && round == ScoreStore.get(i).getRound()) {
                    ScoreStore.get(i).setScore(score);
                }
            }
        }

    }

    private static void inquireStudentStatus() {
        System.out.println("조회하고 싶은 수강생 상태를 입력하세요");
        System.out.println("1.좋음 2.보통 3.요주인물 0.취소");
        String[] statusArr = {"좋음", "보통", "요주인물"};
        int status = sc.nextInt();
        List<Student> students = new ArrayList<>();
        if (status == 0) {
            displayStudentView();
        } else if (status >= 1 && status <= 3) {
            for (Map.Entry<String, Student> entry : studentStore.entrySet()) {
                if (entry.getValue().getStatus() == statusArr[status - 1]) {
                    students.add(entry.getValue());
                }
            }
        } else {
            System.out.println("/n 잘못된 입력입니다");
            inquireStudentStatus();
        }
        for (Student student : students) {
            System.out.println("학생 ID: " + student.getStudentId());
            System.out.println("학생 이름: " + student.getStudentName());
            System.out.println("학생 상태: " + student.getStatus());
            System.out.println();
        }
    }



    /*********** 수강생의 특정 과목 회차별 등급 조회*********/
    private static void inquireRoundGradeBySubject() {
        String studentId = inputStudentId(); // 관리할 수강생 고유 번호

        // 과목 선택
        int subjectId = getsubjectId();

        // 회차 선택
        int round = getRound();

        // 해당 수강생의 해당 과목 및 회차에 대한 등급 조회
        String grade = calculateGrade(studentId, subjectId, round);

        // 결과 출력
        System.out.println("학생 ID " + studentId + "의 " +
                "과목 ID " + subjectId + "의 " +
                "회차 " + round + "의 등급은 " + grade + "입니다.");
    }

    // 등급 계산 메서드
    private static String calculateGrade(String studentId, int subjectId, int round) {
        //수강생 점수 가져오기 (scoreStore)
        int score = getScoreFromStore(studentId, subjectId,round);

        if (score == -1){
            return "잘못된 과목 번호입니다.";
        }
        // 필수 과목의 등급 기준
        if (subjectId >= 1 && subjectId <= 5) {
            if (score >= 95) {
                return "A";
            } else if (score >= 90) {
                return "B";
            } else if (score >= 80) {
                return "C";
            } else if (score >= 70) {
                return "D";
            } else if (score >= 60) {
                return "F";
            } else {
                return "N";
            }
        }
        // 선택 과목의 등급 기준
        else if (subjectId >= 6 && subjectId <= 9) {
            if (score >= 90) {
                return "A";
            } else if (score >= 80) {
                return "B";
            } else if (score >= 70) {
                return "C";
            } else if (score >= 60) {
                return "D";
            } else if (score >= 50) {
                return "F";
            } else {
                return "N";
            }
        } else {
            // 과목 ID가 범위를 벗어난 경우
            return "잘못된 과목 번호입니다.";
        }

    }

    private static int getScoreFromStore(String studentId, int subjectId, int round){
        for (Score score : ScoreStore) {
            if (score.getStudentId().equals(studentId)
                    && score.getSubjectId() == subjectId
                    && score.getRound() == round) {
                return score.getScore();
            }
        }
        return -1;
    }




    /************ 수강생 정보 삭제 ************/
    public static void deleteStudent(String studentIdToDelete) {
        // 해당 아이디의 수강생이 존재하는지 확인
        if (studentStore.containsKey(studentIdToDelete)) {
            // 수강생 정보 삭제
            studentStore.remove(studentIdToDelete);
            System.out.println(studentIdToDelete + " 학생의 정보를 삭제했습니다.");

            // 해당 수강생의 수강과목 정보 가져오기
            List<Subject> subjectsToRemove = management.get(studentIdToDelete);

            // 해당 수강생의 수강과목 정보 삭제
            management.remove(studentIdToDelete);
            System.out.println(studentIdToDelete + " 학생의 수강과목 정보를 삭제했습니다.");

            // 해당 수강생의 점수 정보 삭제
            removeScores(studentIdToDelete);

            // 해당 수강생의 수강과목을 모든 수강생 목록에서도 제거
            for (Map.Entry<String, List<Subject>> entry : management.entrySet()) {
                List<Subject> subjects = entry.getValue();
                subjects.removeAll(subjectsToRemove);
            }

        } else {
            System.out.println("해당 아이디의 수강생이 존재하지 않습니다.");
        }
    }

    private static void removeScores(String studentId) {
        // 해당 수강생의 점수 정보 가져오기
        List<Score> scoresToRemove = new ArrayList<>();
        for (Score score : ScoreStore) {
            if (score.getStudentId().equals(studentId)) {
                scoresToRemove.add(score);
            }
        }
        // 해당 수강생의 점수 정보 삭제
        ScoreStore.removeAll(scoresToRemove);

        System.out.println(studentId + " 학생의 점수 정보를 삭제했습니다.");
    }

    private static void deleteStudent() {
        System.out.print("\n삭제할 수강생의 아이디를 입력하세요: ");
        String studentIdToDelete = sc.next();
        deleteStudent(studentIdToDelete);
    }
}
