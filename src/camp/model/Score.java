package camp.model;

import java.util.HashMap;
import java.util.Map;

public class Score {
    private String scoreId;
    private int[] scores;

    //seq는 SC+점수인덱스 -> Score 생성자 호출될 때 scoreId에 SC+점수인덱스 저장됨

    public Score(String seq) {
        this.scoreId = seq;
    }
    public void addScore(int num, int score){

    }

    // Getter
    public String getScoreId() {
        return scoreId;
    }




}
