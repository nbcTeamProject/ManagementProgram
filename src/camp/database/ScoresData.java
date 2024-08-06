package camp.database;
import camp.model.Score;

import java.util.ArrayList;
import java.util.List;

public class ScoresData {
    private List<Score> scores;

    public List<Score> getScores(){
        return scores;
    }
    public void setInitScores(){
        this.scores = new ArrayList<>();
    }
}

