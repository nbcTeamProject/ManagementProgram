
package camp.service;
import java.util.List;
import java.util.Scanner;
import camp.CampManagementApplication;
import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;
import camp.database.ScoresData;

public class ScoreService extends Score {
    private  String scoreId;
    private  int testNum;
    private  int testScore;
    private  List<Score>scores;
    private  Scanner sc = new Scanner(System.in);


    public ScoreService(String scoreId, int test, int testScore) {
        super(scoreId, test, testScore);
        this.scoreId    = super.getScoreId();
        this.testNum    = super.getTestNum();
        this.testScore  = super.getTestScore();
        ScoresData scoresData = new ScoresData();
        this.scores = scoresData.getScores();
    }

    //시험 회차 입력 받아서 Score(점수 등록이 되어있는 객체) 객체 반환하는 메서드
    public Score getScore() {
        System.out.print("\n시험 회차를 입력해주세요:");
        int tempTest = sc.nextInt();

        for (int i = 0; i < scores.size(); i++) {
            Score tempScore = scores.get(i);
            if(tempScore.getTestNum()==tempTest){
                System.out.println("해당 Score 객체를 찾았습니다.");
                return tempScore;
            }
        }
        System.out.println("해당 Score 객체를 찾지못했습니다.");
        return null;
    }
    // 시험 회차 입력받아서 1~10 사이 수인지 확인하는 메서드
    // 시험점수 등록할 때 사용
    public boolean IsTestIn(int testNum){
        if(testNum>=1 && testNum <=10){
            return true;
        }
        return false;
    }

    public boolean IsTestScoreIn(int testSc){
        if(testSc>=0 && testSc <=100){
            return true;
        }
        return false;
    }

    public void setTestScore(int sc) {
        testScore  = sc;}

    public  char makeGrade(int score, Subject subject){
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
    public  char makeAveerageGrade(Student student, Subject subject){
        char averageGrade;
        int scoreSum = 0;
        int testCount = 0;
        int averagescore = 0;
        for(Service service : serviceStore){
            if(service.getStudentId().equals(student.getStudentId())&& service.getSubjectId().equals(subject.getSubjectId())){
                testCount++;
                scoreSum+=service.getTestscore();
            }
        }
        averagescore = scoreSum / testCount;
        averageGrade = makeGrade(averagescore,subject);
        return averageGrade;
    }
    public void setTestscore(Student student,Subject subject, Score score){
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
    }
}
