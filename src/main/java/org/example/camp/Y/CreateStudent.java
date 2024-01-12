package org.example.camp.Y;

import org.example.camp.CampManagementApplication;
import org.example.camp.model.Student;
import org.example.camp.model.Subject;

import java.util.*;

public class CreateStudent {
    Scanner sc = new Scanner(System.in);
    HashMap<String, Student> studentStore;
    Subject subject;
    String[] titleList = {"Java", "객체지향", "Spring", "JPA", "MySQL", "디자인 패턴", "Spring", "SecurityReids", "MongoDB"};
    private static String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static String SUBJECT_TYPE_CHOICE = "CHOICE";

    private static List<Subject> newSubjectStore;

    public CreateStudent() {
        studentStore = new HashMap<>();
        newSubjectStore = new ArrayList<>();
    }
    public void mkStudent() {
        List<Integer> index;
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.nextLine();
        int choiceStatus = sc.nextInt();
        System.out.println("수강생의 상태를 선택해주세요\n[1.좋음 , 2.보통 ,3.요주인물]");
        sc.nextLine();
        // 기능 구현 (필수 과목, 선택 과목)
        Student student = new Student(CampManagementApplication.getSequence(), studentName,choiceStatus);
        System.out.println(student);
        studentStore.put(student.getStudentId(), student);// 수강생 인스턴스 생성 예시 코드
        CampManagementApplication.setStudentStore(student.getStudentId(),student);
        index = selectSubject();
        for (int idName : index) {
            if (idName == 0) {
                continue;
            }
            String subJectName = titleList[idName - 1];
            String type;
            if (idName < 5) {
                type = SUBJECT_TYPE_MANDATORY;
            } else {
                type = SUBJECT_TYPE_CHOICE;
            }
            subject = new Subject(idName, subJectName, type);
            newSubjectStore.add(subject);
        }
        // 기능 구현
        for (Subject a : newSubjectStore) {
            System.out.println(a);
        }
        CampManagementApplication.setManagement(student.getStudentId(),newSubjectStore);
        System.out.println("수강생 등록 성공!\n");
    }

    //예외처리
    //선택과목 중복,최소 수강과목 개수 부족
    private List<Integer> selectSubject() {
        List<Integer> subSelectList = new ArrayList<>();
        int num;
        int cnt1 = 0;
        int cnt2 = 0;
        while (true) {
            System.out.println("\n과목을 선택하세요");
            System.out.println("[필수]\n1.Java 2.객체지향 3.Spring 4.JPA 5.MySQL" +
                    "\n[선택]\n6.디자인 패턴 7.Spring 8.SecurityRedis 9.MongoDB");
            System.out.println("종료를 원하신다면 0을 눌러주세요");
            num = sc.nextInt();
            sc.nextLine();
            if (num == 0) {
                if (cnt1 < 3 || cnt2 < 2) {
                    System.out.println("필수과목을 " + cnt1 + "개 " + "선택과목을 " + cnt2 + "개 선택하셨습니다.");
                    System.out.println("필수과목은 3개 이상, 선택과목은 2개 이상을 선택하세요");
                } else {
                    return subSelectList;
                }
            } else {
                //필수과목
                if (num > 0 && num <= 5) {
                    if (!subSelectList.contains(num)) {
                        cnt1++;
                        subSelectList.add(num);
                        for (int a : subSelectList) {
                            System.out.println(a);
                        }
                    } else {
                        System.out.println("\n\n중복된 과목입니다 다시 선택하세요\n\n");
                    }
                }
                //선택과목
                else if (5 < num && num < 10) {
                    if (!subSelectList.contains(num)) {
                        cnt2++;
                        subSelectList.add(num);
                    } else {
                        System.out.println("중복된 과목입니다 다시 선택하세요");
                    }
                }
            }

        }

    }
}