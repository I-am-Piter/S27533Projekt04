package Events;

import java.util.EventObject;

public class ScoreEvent extends EventObject {
    int score;
    public ScoreEvent(Object source,int score) {
        super(source);
        this.score = score;
    }
    public int getScore(){
        return score;
    }
}
