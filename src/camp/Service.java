package camp;
import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.List;

public class Service {

    private String studentId;
    private String subjectId;
    private String scoreId;
    private int test;
    private int testscore;
    private char grade;

    public Service(Score score, Subject subject, String studentId) {
        this.studentId      = studentId;
        this.subjectId      = subjectId;
        this.scoreId        = score.getScoreId();
        this.test           = score.getTest();
        this.testscore      = score.getTestscore();
        this.grade          = makeGrade(this.testscore,subject);
        System.out.println("Service 객체가 성공적으로 만들어졌습니다.");
        System.out.println("Service 객체의 testscore: " + testscore);
        System.out.println("Service 객체의 grade: "+ grade);
        System.out.println("Service 객체의 subjectId: " + subjectId);
        System.out.println("Service 객체의 studentId: " + studentId);

    }
    //Subject 객체를 담은 리스트와 과목 이름으로 Subject 객체 찾는 메서드
    public static Subject findSubject(List<Subject> subjectStore, String subject){
        Subject answer = subjectStore.get(0);
        for (int i = 0; i < subjectStore.size(); i++) {
            Subject sub = subjectStore.get(i);
            if (sub.getSubjectName().equals(subject)) {
                answer = sub;
                break;
            } else if (i == subjectStore.size()-1) {
                System.out.println("해당 Subject 객체를 찾지 못했습니다.");
            }
        }
        return answer;
    }

    // 과목 이름으로 과목 Id 찾는 메서드
    public static String findSubjectId(String subName){
        String subId = "";
        switch(subName){
            case "Java" :
                subId = "SU1";
                break;
            case "객체지향" :
                subId = "SU2";
                break;
            case "Spring" :
                subId = "SU3";
                break;
            case "JPA" :
                subId = "SU4";
                break;
            case "MySQL" :
                subId = "SU5";
                break;
            case "디자인 패턴" :
                subId = "SU6";
                break;
            case "Spring Security" :
                subId = "SU7";
                break;
            case "Redis" :
                subId = "SU8";
                break;
            case "MongoDB" :
                subId = "SU9";
                break;
        }
        return subId;
    }
    // 매개변수로 받은 점수가  minNum~maxNum사이 인지 판별해 true false 반환하는 메서드
    public static boolean IsIn(int num, int minNum, int maxNum){
        boolean answer = true;
        if(num <  minNum || num > maxNum){
            answer = false;
        }
        return answer;
    }
    //점수와 과목 이름을 매개변수로 받아 grade 산정하는 메서드
    public static char makeGrade(int score,Subject subject){
        String subjectType = subject.getSubjectType();
        char grade='z';
        if(subjectType.equals("MANDATORY")){
            if(score>=95){
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
        }else if(subjectType.equals("CHOICE")){
            if(score>=90){
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

    //gettet
    public String getStudentId() {
        return studentId;
    }
    public String getSubjectId() {
        return subjectId;
    }
    public String getScoreId() {return scoreId;}
    public int getTest() { return test;}
    public int getTestscore() { return testscore; }

    //setter
    public void setTestscore(int score,Subject subject){
        this.testscore = score;
        this.grade = makeGrade(this.testscore,subject);
        System.out.println("수정된 Service 객체의 grade: "+ grade);
    }

}