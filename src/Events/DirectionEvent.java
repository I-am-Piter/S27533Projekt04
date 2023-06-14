package Events;

import Enums.Direction;

import java.util.EventObject;

public class DirectionEvent extends EventObject {

    Direction direction;

    public DirectionEvent(Direction direction, Object source){
        super(source);
        this.direction = direction;
    }
    public Direction getDirection(){
        return direction;
    }
}
