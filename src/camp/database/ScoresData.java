package camp.database;
import camp.model.Score;
import camp.model.Student;

import java.util.ArrayList;
import java.util.List;

public class ScoresData {
    private List<Score> scores;

    public  List<Score> getScores(){
        return scores;
    }
    public  void setInitScores(){
        scores = new ArrayList<>();
    }
    public  void addScore(Score score){
        scores.add(score);
    }
    public  int getScoresSize(){
        return scores.size();
    }

}

