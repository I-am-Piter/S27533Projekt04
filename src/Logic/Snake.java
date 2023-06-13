package Logic;

import Events.AteFoodEvent;
import Events.AteFoodListener;

import java.util.ArrayList;

public class Snake
        implements AteFoodListener {
    ArrayList<Segment> body;
    public Snake(){
        this.body = new ArrayList<>();
    }

    @Override
    public void ateFood(AteFoodEvent afe) {
    }
}
