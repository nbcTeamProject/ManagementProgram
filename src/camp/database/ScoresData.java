package camp.database;
import camp.model.Score;
import camp.model.Student;

import java.util.ArrayList;
import java.util.List;

public class ScoresData {
    private static List<Score> scores;

    public static List<Score> getScores(){
        return scores;
    }
    public static void setInitScores(){
        scores = new ArrayList<>();
    }
    public static void addScore(Score score){
        scores.add(score);
    }
    public static int getScoresSize(){
        return scores.size();
    }

}

