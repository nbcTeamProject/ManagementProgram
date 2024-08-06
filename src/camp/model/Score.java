

package camp.model;

public class Score {
    private String scoreId;
    private int testNum;
    private int testScore;
    private Student regiStudent;
    private Subject regiSubject;

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
    public int getTestNum() { return testNum;}
    public int getTestScore() { return testScore; }
    public Student getRegiStudent(){return regiStudent;}
    public Subject regiSubject(){return regiSubject;}

    //setter
    public void setScoreId(String scoreId){
        this.scoreId = scoreId;
    }
    public void setTestNum(int testNum){
        this.testNum = testNum;
    }
    public void setRegiStudent(Student regiStudent){
        this.regiStudent = regiStudent;
    }
    public void setregiSubject(Subject regiSubject){
        this.regiSubject = regiSubject;
    }
}