

package camp.model;

public class Score {
    private String scoreId;
    private int testNum;
    private int testScore;

    /* CampManagementApplication  주석 score 객체만들기 */
    public Score(String scoreId, int testNum, int testScore) {
        this.scoreId = scoreId;
        this.testNum = testNum;
        this.testScore = testScore;
    }

    // Getter
    public String getScoreId() {
        return scoreId;
    }
    public int testNum() { return testNum;}
    public int testScore() { return testScore; }

}