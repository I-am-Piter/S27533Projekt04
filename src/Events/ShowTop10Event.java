package Events;

import java.util.EventObject;

public class ShowTop10Event extends EventObject {
    String[] names;
    int[] scores;
    public ShowTop10Event(Object source,String[] names,int[] scores) {
        super(source);
        this.names = names;
        this.scores = scores;
    }
    public String[] getNames(){
        return names;
    }

    public int[] getScores() {
        return scores;
    }
}
