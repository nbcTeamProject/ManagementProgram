
package camp.model;
import java.util.List;
import java.util.Scanner;
import camp.CampManagementApplication;

public class ScoreManager extends Score{
    private static String scoreId;
    private static int test;
    private static int testScore;
    private static Scanner sc = new Scanner(System.in);
    private static List<Score>scoreStore = CampManagementApplication.getScoreStore();


    public ScoreManager(String scoreId, int test, int testScore) {
        super(scoreId, test, testScore);
        this.scoreId    = super.getScoreId();
        this.test       = super.getTest();
        this.testScore  = super.getTestscore();
    }

    //시험 회차 입력 받아서 Score(점수 등록이 되어있는 객체) 객체 반환하는 메서드
    // 이 메서드 사용 후 sc.nextLine() 사용 권장
    public static Score getScore() {
        System.out.print("\n시험 회차를 입력해주세요:");
        int tempTest = sc.nextInt();

        for (int i = 0; i < scoreStore.size(); i++) {
            Score tempScore = scoreStore.get(i);
            if(tempScore.getTest()==tempTest){
                System.out.println("해당 Score 객체를 찾았습니다.");
                return tempScore;
            }
        }
        System.out.println("해당 Score 객체를 찾지못했습니다.");
        return null;
    }
    // 시험 회차 입력받아서 1~10 사이 수인지 확인하는 메서드
    // 시험점수 등록할 때 사용
    public static boolean IsTestIn(int testNum){
        if(testNum>=1 && testNum <=10){
            return true;
        }
        return false;
    }

    public static boolean IsTestScoreIn(int testSc){
        if(testSc>=0 && testSc <=100){
            return true;
        }
        return false;
    }

    public static void setTestScore(int sc) {
        testScore  = sc;}
}
