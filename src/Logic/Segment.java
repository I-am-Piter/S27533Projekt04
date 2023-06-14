package Logic;

import Enums.Direction;
import Enums.Type;

public class Segment {
    Type type;
    int x;
    int y;

    public Segment(Type type,int x,int y){
        this.type = type;
        this.x = x;
        this.y = y;
    }
    public void setDirection(Direction direction){}

    public Direction getDirection(){return null;}
}
