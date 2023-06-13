package Logic;

import Enums.Direction;
import Enums.Type;

public class Segment {
    Direction direction;
    Type type;
    int x;
    int y;

    public Segment(Direction direction,Type type,int x,int y){
        this.direction = direction;
        this.type = type;
        this.x = x;
        this.y = y;
    }
}
