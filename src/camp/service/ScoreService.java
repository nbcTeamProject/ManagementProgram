
package camp.service;

import java.util.List;
import java.util.Scanner;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;
import camp.database.ScoresData;

public class ScoreService {
    private Scanner sc = new Scanner(System.in);


    // 점수 등록할 때 사용 매개변수로 시험 회차 받아야함
    public Score getScore(Student student, Subject subject, int testNum) {
        List<Score> scores = ScoresData.getScores();
        for (int i = 0; i < scores.size(); i++) {
            Score tempScore = scores.get(i);
            if (tempScore.getTestNum() == testNum && tempScore.getRegiStudent().equals(student) && tempScore.getRegiSubject().equals(subject)) {
                System.out.println("해당 시험 데이터를 찾았습니다.");
                return tempScore;
            }
        }
        System.out.println("해당 시험 데이터를 찾지못했습니다.");
        return null;
    }


    // 오버로딩, 점수 등록할 때 사용 , 매개변수로 시험 점수 받아야함
    public boolean registScore(Student student, Subject subject, Score score, int testScoreum) {
        score.setRegiStudent(student);
        score.setRegiSubject(subject);
        score.setTestScore(testScoreum);
        score.setGrade(testScoreum, subject);
        score.setTestNum(score.getTestNum());
        score.setScoreId(score.getScoreId());
        System.out.println("등록 완료");
        return true;
    }

    // 시험 회차 입력받아서 1~10 사이 수인지 확인하는 메서드
// 시험점수 등록할 때 사용
    public boolean IsTestIn(int testNum) {
        if (testNum >= 1 && testNum <= 10) {
            return true;
        }
        return false;
    }

    public boolean IsTestScoreIn(int testSc) {
        if (testSc >= 0 && testSc <= 100) {
            return true;
        }
        return false;
    }


    public char makeGrade(int score, Subject subject) {
        String subjectType = subject.getSubjectType();
        char grade = 'z';
        if (subjectType.equals("MANDATORY")) {
            if (score >= 95) {
                grade = 'A';
            } else if (score >= 90) {
                grade = 'B';
            } else if (score >= 80) {
                grade = 'C';
            } else if (score >= 70) {
                grade = 'D';
            } else if (score >= 60) {
                grade = 'F';
            } else {
                grade = 'N';
            }
        } else if (subjectType.equals("CHOICE")) {
            if (score >= 90) {
                grade = 'A';
            } else if (score >= 80) {
                grade = 'B';
            } else if (score >= 70) {
                grade = 'C';
            } else if (score >= 60) {
                grade = 'D';
            } else if (score >= 50) {
                grade = 'F';
            } else {
                grade = 'N';
            }
        }
        return grade;
    }


}






