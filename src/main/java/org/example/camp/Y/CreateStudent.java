package org.example.camp.Y;

import org.example.camp.CampManagementApplication;
import org.example.camp.model.Student;
import org.example.camp.model.Subject;

import java.util.*;

public class CreateStudent {
    private Scanner sc = new Scanner(System.in);
    private final String[] titleList = {"Java", "객체지향", "Spring", "JPA", "MySQL", "디자인 패턴", "Spring", "SecurityReids", "MongoDB"};
    private String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private String SUBJECT_TYPE_CHOICE = "CHOICE";
    private final int MIN_MANDATORY_NUM = 3;
    private final int MIN_CHOICE_NUM = 2;
    private int mandatoryIndex = 0;
    private int choiceIndex = 0;
    private final List<Subject> newSubjectStore;
    private final List<Integer> subSelectList;

    public CreateStudent() {
        newSubjectStore = new ArrayList<>();
        subSelectList = new ArrayList<>();
    }

    public void mkStudent() {
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.nextLine();
        System.out.println("수강생의 상태를 선택해주세요\n[1.좋음 , 2.보통 ,3.요주인물]");
        int choiceStatus = sc.nextInt();

        // 기능 구현 (필수 과목, 선택 과목)
        Student student = new Student(CampManagementApplication.getSequence(), studentName, choiceStatus);
        System.out.println(student);
        CampManagementApplication.setStudentStore(student.getStudentId(), student);

        selectSubject();

        for (int idName : subSelectList) {
            String subjectName = titleList[idName - 1];
            String type = idName <= 5 ? SUBJECT_TYPE_MANDATORY : SUBJECT_TYPE_CHOICE;

            Subject subject = new Subject(idName, subjectName, type);
            newSubjectStore.add(subject);
        }
        // 기능 구현
        for (Subject a : newSubjectStore) {
            System.out.println(a);
        }
        CampManagementApplication.setManagement(student.getStudentId(), newSubjectStore);
        System.out.println("수강생 등록 성공!\n");

    }

    //예외처리
    //선택과목 중복,최소 수강과목 개수 부족
    private void selectSubject() {
        int num;
        boolean flag = true;
        while (flag) {
            System.out.println("\n과목을 선택하세요");
            System.out.println("""
                    [필수]
                    1.Java 2.객체지향 3.Spring 4.JPA 5.MySQL
                    [선택]
                    6.디자인 패턴 7.Spring 8.SecurityRedis 9.MongoDB""");
            System.out.println("종료를 원하신다면 0을 눌러주세요");
            num = sc.nextInt();

            switch (num) {
                case 0 -> flag = checkCondition();
                case 1, 2, 3, 4, 5, 6, 7, 8, 9 -> addSubjectSelectList(num);
                default -> System.out.println("잘못된 입력입니다.");
            }
        }
    }
    private boolean checkCondition() {
        if (mandatoryIndex < MIN_MANDATORY_NUM || choiceIndex < MIN_CHOICE_NUM) {
            System.out.println("필수과목을 " + mandatoryIndex + "개 " + "선택과목을 " + choiceIndex + "개 선택하셨습니다.");
            System.out.println("필수과목은 3개 이상, 선택과목은 2개 이상을 선택하세요");
            return true;
        }
        return false;
    }

    private void addSubjectSelectList(int num) {
        if (!subSelectList.contains(num)) {
            subjectTypeIndexUp(num);
            subSelectList.add(num);
        } else {
            System.out.println("\n중복된 과목입니다 다시 선택해주세요.");
        }
    }
    // 조건 충족 체크
    private void subjectTypeIndexUp(int num) {
        if (num <= 5) {
            ++mandatoryIndex;
        }
        if (num > 5) {
            ++choiceIndex;
        }
    }

}