package org.example.camp.P;

import org.example.camp.model.Score;
import org.example.camp.model.Student;
import org.example.camp.model.Subject;


public class Search {
    public String getGrade(Student student, Subject subject) {
        String scoreIdToSearch = student.getStudentId() + "_" + subject.getSubjectId();

        for (Score score : scores) {
            if (score.getScoreId().equals(scoreIdToSearch)) {
                int scoreValue = score.getScoreValue(); // 점수 값을 가져오도록 수정

                if (scoreValue >= 95) {
                    return "A";
                } else if (scoreValue >= 90 && scoreValue <=94) {
                    return "B";
                } else if (scoreValue >= 80 && scoreValue <=89) {
                    return "C";
                } else if (scoreValue >= 70 && scoreValue <=79) {
                    return "D";
                } else {
                    return "F";
                }
            }
        }

        return "0"; // 성적 정보가 없는 경우 N/A 리턴
    }
}
