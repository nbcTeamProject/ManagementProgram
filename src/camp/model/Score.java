
package camp.model;

public class Score {
    private String scoreId;
    private String studentId;
    private String subjectName;
    private int test;
    private int testscore;

    /* CampManagementApplication  주석 score 객체만들기 */
    public Score(String scoreId, String studentId, String subjectName, int test, int testscore) {
        this.scoreId = scoreId;
        this.studentId = studentId;
        this.subjectName = subjectName;
        this.test = test;
        this.testscore = testscore;
    }

    // Getter
    public String getScoreId() {
        return scoreId;
    }
    public String getStudentId() {return studentId;}
    public String getSubjectName() { return subjectName;}
    public int getTest() { return test;}
    public int getTestscore() { return testscore; }

}
