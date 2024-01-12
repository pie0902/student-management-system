package org.example.camp.P;

import org.example.camp.model.Student;

import java.util.List;

public class Delete {

    // 고유 번호로 수강생 삭제
    public static void deleteStudentById(List<Student> studentStore, String studentId) {
        Student studentDelete = null; //초기값을 null로 초가화

        // 수강생 목록에서 해당 id를 가진 수강생 찾기
        for (Student student : studentStore) {
            if (student.getStudentId().equals(studentId)) {
                studentDelete = student;
                break;
            }
        }

        // 찾은 수강생 삭제
        if (studentDelete != null) {
            studentStore.remove(studentDelete);
            System.out.println("수강생 정보 삭제 성공!");
        } else {
            System.out.println("해당 id를 가진 수강생을 찾을 수 없습니다.");
        }
    }
}
