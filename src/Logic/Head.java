package Logic;

import Enums.Direction;
import Enums.Type;

public class Head extends Segment{
    Direction direction;
    public Head(Direction direction,Type type, int x, int y) {
        super(type, x, y);
        this.direction = direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public Direction getDirection(){
        return direction;
    }
}
