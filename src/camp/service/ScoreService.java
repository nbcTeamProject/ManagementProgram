
package camp.service;

import java.util.List;
import java.util.Scanner;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;
import camp.database.ScoresData;

public class ScoreService {
    private Scanner sc = new Scanner(System.in);

    //시험 회차 입력 받아서 Score(점수 등록이 되어있는 객체) 객체 반환하는 메서드
    // 점수 수정할 때 사용
    public Score getScore(Student student, Subject subject) {
        List<Score> scores = ScoresData.getScores();
        int tempTest = sc.nextInt();
        if (IsTestIn(tempTest)) {// 시험 회차 1~10 일때
            for (int i = 0; i < scores.size(); i++) {
                Score tempScore = scores.get(i);
                if (tempScore.getTestNum() == tempTest && tempScore.getRegiStudent().equals(student) && tempScore.getRegiSubject().equals(subject)) {
                    System.out.println("해당 시험 데이터를 찾았습니다.");
                    return tempScore;
                }
            }
            System.out.println("해당 시험 데이터를 찾지못했습니다.");
            return null;
        } else { // 시험 회차 1~10 아닐 때
            System.out.println("잘못된 시험 회차입니다.");
            return null;
        }
    }

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
    // 시험 점수 입력받고 0~100 점 사이인지 검사해서 Score 객체에 점수 저장하는 메서드
// 시험 점수 수정할 때 사용
    public boolean registScore(Student student, Subject subject, Score score) {
        System.out.println("0~100 점 사이의 점수를 입력할 수 있습니다.");
        System.out.println("등록할 점수를 입력하세요: ");
        int tempTestScore = sc.nextInt();
        if (IsTestScoreIn(tempTestScore)) {
            score.setRegiStudent(student);
            score.setRegiSubject(subject);
            score.setTestScore(tempTestScore);
            score.setGrade(tempTestScore, subject);
            score.setTestNum(score.getTestNum());
            score.setScoreId(score.getScoreId());
            System.out.println("등록 완료");
            return true;
        }
        return false;
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

    /*public void setTestscore(Student student,Subject subject, Score score){
        System.out.println(student.getStudentName()+" 학생의 점수를 수정합니다.");
        System.out.println("점수를 입력하세요: ");
        int testSc = sc.nextInt();
        if(ScoreService.IsTestScoreIn(testSc)){
            this.testScore = testSc;
            this.grade = makeGrade(testSc,subject);
            System.out.println(student.getStudentName()+" 학생의 " +subject.getSubjectName()+" 과목 점수가 "+this.testScore+" 점으로 수정되었습니다.");

        }else{
            System.out.println("잘못된 점수를 입력하였습니다. 점수 조회 화면으로 돌아갑니다.");
            updateRoundScoreBySubject();
        }
    }*/
}






