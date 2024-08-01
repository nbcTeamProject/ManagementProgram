package camp.model;

public class Score {
    private String scoreId;
    private int score;


    //seq는 SC+점수인덱스 -> Score 생성자 호출될 때 scoreId에 SC+회차번호 저장됨
    // 회차 번호 물어볼때 생성자 호출
    public Score(String seq) {
        this.scoreId = seq;
    }


    // Getter
    public String getScoreId() {
        return scoreId;
    }

    //setter
    // 점수 입력받을 때 호출
    public void setScore(int sc){
        score = sc;
    }



}
