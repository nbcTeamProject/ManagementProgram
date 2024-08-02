package camp;
import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

public class Service {

    private String studentId;
    private String subjectId;
    private String scoreId;
    private int test;
    private int testscore;

    public Service(Score score, String subjectId, String studentId) {
        this.studentId      = studentId;
        this.subjectId      = subjectId;
        this.scoreId        = score.getScoreId();
        this.test           = score.getTest();
        this.testscore      = score.getTestscore();
        System.out.println("Service 객체가 성공적으로 만들어졌습니다.");
        System.out.println("Service 객체의 testscore: " + testscore);
        System.out.println("Service 객체의 subjectId: " + subjectId);
        System.out.println("Service 객체의 studentId: " + studentId);

    }
    //
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
    public void setTestscore(int score){
        this.testscore = score;
    }
}
