package camp;
import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.List;

public class Service {

    private String studentId;
    private String studentName;
    private String subjectId;
    private String scoreId;
    private int test;
    private int testScore;
    private char grade;

    public Service(Score score, Subject subject, String studentId) {
        this.studentId      = studentId;
        this.subjectId      = subject.getSubjectId();
        this.scoreId        = score.getScoreId();
        this.test           = score.getTest();
        this.testScore      = score.getTestscore();
        this.grade          = makeGrade(this.testScore,subject);
        System.out.println("Service 객체가 성공적으로 만들어졌습니다.");
        System.out.println("Service 객체의 testScore: " + testScore);
        System.out.println("Service 객체의 grade: "+ grade);
        System.out.println("Service 객체의 subjectId: " + subjectId);
        System.out.println("Service 객체의 studentId: " + studentId);

    }
    //Subject 객체를 담은 리스트와 과목 이름으로 Subject 객체 찾는 메서드
    public static Subject findSubject(List<Subject> subjectStore, String subject){
        Subject answer;
        for (int i = 0; i < subjectStore.size(); i++) {
            Subject sub = subjectStore.get(i);
            if (sub.getSubjectName().equals(subject)) {
                System.out.println("해당 Subject 객체를 찾았습니다.");
                return sub;
            }
        }
        System.out.println("해당 Subject 객체를 찾지 못했습니다.");
        return null;
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
    // service 리스트와, 시험 회차, Student 객체, Subject 객체 입력 받아서 service 객체 반환하는 메서드
    public static Service findService(List<Service>services, int test, Student student, Subject subject ){
        for (int i = 0; i < services.size(); i++) {
            Service service = services.get(i);
            if (service.getTest() == test && service.getSubjectId().equals(subject.getSubjectId())&&service.getStudentId().equals(student.getStudentId())) {
                System.out.println("해당 Service 객체를 찾았습니다.");
                return service;
            }
        }
        System.out.println("해당 Service 객체를 찾지 못했습니다.");
        return null;
    }
    // student 리스트와 studentId 받아서 Student 객체 반환하는 메서드
    public static Student findStudent(List<Student>students, String studentId ){
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student.getStudentId().equals(studentId)) {
                System.out.println("해당 Student 객체를 찾았습니다.");
                return student;
            }
        }
        System.out.println("해당 Student 객체를 찾지 못했습니다");
        return null;
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
    public int getTestscore() { return testScore; }
    public char getGrade() {return grade;}


    //setter
    public void setTestscore(int score,Subject subject){
        this.testScore = score;
        this.grade = makeGrade(this.testScore,subject);
        System.out.println("수정된 Service 객체의 grade: "+ grade);
    }

}
