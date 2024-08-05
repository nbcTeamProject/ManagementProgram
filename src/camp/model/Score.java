

package camp.model;

public class Score {
    private String scoreId;
    private int test;
    private int testscore;

    /* CampManagementApplication  주석 score 객체만들기 */
    public Score(String scoreId, int test, int testscore) {
        this.scoreId = scoreId;
        this.test = test;
        this.testscore = testscore;
    }

    // Getter
    public String getScoreId() {
        return scoreId;
    }
    public int getTest() { return test;}
    public int getTestscore() { return testscore; }

}
